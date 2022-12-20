package live.anshang.java2final.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class IssueDTO {

  List<Issue> issueList;
  private int total;
  private int opening;
  private int closed;
  private double avg;
  private long max;
  private long min;
  private long diff;
  private double variance;
}
