package live.anshang.java2final.Spider;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import live.anshang.java2final.DTO.*;
import org.joda.time.DateTime;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.regex.*;

public class GithubSpider {

    static String token = "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";
    public static void main(String[] args) {
        getInfoByRepoURL("gnembon", "fabric-carpet");
    }
    private static final Pattern pattern = Pattern.compile("\\[.*\\]", Pattern.DOTALL);

    public static Repository getInfoByRepoURL(String author, String repo) {

        Repository repository = new Repository();
        repository.setAuthor(author);
        repository.setName(repo);

        crabIssue(author, repo, repository);
        crabRelease(author, repo, repository);
        crabCommit(author, repo, repository);

        return repository;
    }

    private static void crabIssue(String author, String repo, Repository repository) {
        List<Issue> issues = repository.getIssues();
        String issuesURL = String.format("https://api.github.com/repos/%s/%s/issues", author, repo);
        try {
            Connection issueCon = Jsoup.connect(issuesURL).ignoreContentType(true).ignoreHttpErrors(true)
                    .header("Authorization", "Bearer " + token).data("filter", "all").data("state", "all");
            int page = 1;
            while (true){
                Document issueDOM = issueCon.data("page", String.valueOf(page)).data("per_page", "100").get();
                Matcher m = pattern.matcher(removeIllegal(issueDOM.body().toString()));
                m.find();
                JSONArray issueObj = (JSONArray) JSON.parse(m.group(0));
                System.out.printf("Done issue page %d\n", page);
                if (issueObj.size() == 0) break;
                issueObj.forEach(j -> {
                    JSONObject json = (JSONObject) j;
                    Issue issue = new Issue();
                    issue.setCreateAt(castToDate(json.get("created_at")));
                    issue.setCloseAt(castToDate(json.get("closed_at")));
                    issue.setStatus((String) json.get("state"));
                    issue.setTitle((String) json.get("title"));
                    issues.add(issue);
                });
                page++;
            }
        } catch (IOException e) {
            System.out.println("No issue");
        }
    }

    private static void crabRelease(String author, String repo, Repository repository) {
        List<Release> releases = repository.getReleases();
        String releaseURL = String.format("https://api.github.com/repos/%s/%s/releases", author, repo);
        try {
            Connection releaseCon = Jsoup.connect(releaseURL).ignoreContentType(true).ignoreHttpErrors(true)
                    .header("Authorization", "Bearer " + token);
            int page = 1;
            while (true){
                Document releaseDOM = releaseCon.data("page", String.valueOf(page)).data("per_page", "100").get();
                JSONArray issueObj = (JSONArray) JSON.parse(releaseDOM.body().text());
                System.out.printf("Done release page %d\n", page);
                if (issueObj.size() == 0) break;
                issueObj.forEach(j -> {
                    JSONObject json = (JSONObject) j;
                    Release release = new Release();
                    release.setCreateAt(castToDate(json.get("created_at")));
                    release.setName((String) json.get("name"));
                    releases.add(release);
                });
                page++;
            }
        } catch (IOException e) {
            System.out.println("No release");
        }
    }

    private static void crabCommit(String author, String repo, Repository repository) {
        List<Commit> commits = repository.getCommits();
        List<Developer> developers = repository.getDevelopers();
        String commitURL = String.format("https://api.github.com/repos/%s/%s/commits", author, repo);
        HashMap<String, Developer> developerHashMap = new HashMap<>();
        try {
            Connection commitCon = Jsoup.connect(commitURL).ignoreContentType(true).ignoreHttpErrors(true)
                    .header("Authorization", "Bearer " + token);
            int page = 1;
            while (true){
                Document commitDOM = commitCon.data("page", String.valueOf(page)).data("per_page", "100").get();
                System.out.printf("Done commit page %d\n", page);
                JSONArray issueObj = (JSONArray) JSON.parse(commitDOM.body().text());
                if (issueObj.size() == 0) break;
                issueObj.forEach(j -> {
                    JSONObject json = (JSONObject) j;
                    Commit commit = new Commit();
                    String mes = json.getJSONObject("commit").getString("message");
                    commit.setName(mes.substring(0, Math.min(mes.length(), 250)));
                    JSONObject authorObj = json.getJSONObject("commit").getJSONObject("author");
                    String authorName = authorObj.getString("name");
                    Developer au;
                    if (developerHashMap.containsKey(authorName)) {
                        au = developerHashMap.get(authorName);
                        au.setCommits(au.getCommits()+1);
                    } else {
                        au = new Developer();
                        developers.add(au);
                        developerHashMap.put(authorName, au);
                        authorObj = json.getJSONObject("author");
                        au.setUrl(authorObj.getString("url"));
                        au.setAvatarUrl(authorObj.getString("avatar_url"));
                        au.setName(authorName);
                        au.setCommits(1);
                    }
                    commit.setDeveloper(au);
                    commits.add(commit);
                });
                page++;
            }
        } catch (IOException e) {
            System.out.println("No commit");
        }
    }

    private static Date castToDate(Object date) {
        String s = (String) date;
        DateTime dateTime = new DateTime(s);
        return dateTime.toDate();
    }

    private static String removeIllegal(String in) {
        return in.replaceAll("\\<.*?\\>|\\\\r\\\\n|[\r\n]|\\*", "");
    }
}
