package spring.testing.dto;

public class FiremanStatsDTO {
  private int firemanCount;
  private int uniqueFiresCount;

  public FiremanStatsDTO(int firemanCount, int uniqueFiresCount) {
      this.firemanCount = firemanCount;
      this.uniqueFiresCount = uniqueFiresCount;
  }

  public int getFiremenCount() {
      return firemanCount;
  }

  public void setFiremenCount(int firemenCount) {
      this.firemanCount = firemenCount;
  }

  public int getUniqueFiresCount() {
      return uniqueFiresCount;
  }

  public void setUniqueFiresCount(int uniqueFiresCount) {
      this.uniqueFiresCount = uniqueFiresCount;
  }
}
