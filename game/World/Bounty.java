package World;

public class Bounty {
    private final String name;
    private final String description;
    private final int requiredProgress;
    private final int experienceReward;
    private final int goldReward;

    private int currentProgress;
    private boolean completed;
    private boolean rewardsClaimed;

    public Bounty(String name, String description,
                 int requiredProgress, int experienceReward,
                 int goldReward) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "Quest name cannot be empty.");
        }

        if (description == null) {
            throw new IllegalArgumentException(
                    "Description cannot be null.");
        }

        if (requiredProgress <= 0
                || experienceReward < 0 || goldReward < 0) {
            throw new IllegalArgumentException(
                    "Invalid quest requirements or rewards.");
        }

        this.name = name;
        this.description = description;
        this.requiredProgress = requiredProgress;
        this.experienceReward = experienceReward;
        this.goldReward = goldReward;
        currentProgress = 0;
        completed = false;
        rewardsClaimed = false;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getExperienceReward() {
        return experienceReward;
    }

    public int getGoldReward() {
        return goldReward;
    }

    public int getCurrentProgress() {
        return currentProgress;
    }

    public int getRequiredProgress() {
        return requiredProgress;
    }

    public boolean isCompleted() {
        return completed;
    }

    public boolean areRewardsClaimed() {
        return rewardsClaimed;
    }

    public void addProgress(int amount) {
        if (amount <= 0 || completed) {
            return;
        }

        currentProgress = Math.min(
                requiredProgress, currentProgress + amount);

        System.out.println(name + ": " + currentProgress
                + "/" + requiredProgress);

        if (currentProgress >= requiredProgress) {
            completed = true;
            System.out.println("Quest completed: " + name);
        }
    }

    public boolean claimRewards() {
        if (!completed || rewardsClaimed) {
            return false;
        }

        rewardsClaimed = true;
        System.out.println("Rewards available: "
                + experienceReward + " XP and "
                + goldReward + " gold.");
        return true;
    }

    public void displayQuest() {
        System.out.println("\nQuest: " + name);
        System.out.println(description);
        System.out.println("Progress: " + currentProgress
                + "/" + requiredProgress);
        System.out.println("Status: "
                + (completed ? "Completed" : "In Progress"));
    }
}

