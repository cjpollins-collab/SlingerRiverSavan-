package World;
import Engine.Party;
import characters.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Chapter {
    private final int chapterNumber;
    private final String title;
    private final String story;
    private final List<Bounty> bountyList;

    private boolean unlocked;
    private boolean completed;

    public Chapter(int chapterNumber, String title,
                   String story) {
        if (chapterNumber <= 0) {
            throw new IllegalArgumentException(
                    "Chapter number must be positive.");
        }

        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "Chapter title cannot be empty.");
        }

        if (story == null) {
            throw new IllegalArgumentException(
                    "Story cannot be null.");
        }

        this.chapterNumber = chapterNumber;
        this.title = title;
        this.story = story;
        this.bountyList = new ArrayList<>();
        this.unlocked = chapterNumber == 1;
        this.completed = false;
    }

    public int getChapterNumber() {
        return chapterNumber;
    }

    public String getTitle() {
        return title;
    }

    public String getStory() {
        return story;
    }

    public boolean isUnlocked() {
        return unlocked;
    }

    public boolean isCompleted() {
        return completed;
    }

    public List<Bounty> getBounty() {
        return Collections.unmodifiableList(bountyList);
    }

    public void addBounty(Bounty bounty) {
        if (bountyList != null && !bountyList.contains(bounty)) {
            bountyList.add(bounty);
        }
    }

    public void unlock() {
        unlocked = true;
        System.out.println("Chapter unlocked: " + title);
    }

    public boolean completeChapter() {
        if (!unlocked || completed) {
            return false;
        }

        for (Bounty bounty : bountyList) {
            if (!bounty.isCompleted()) {
                System.out.println(
                        "Complete all quests before finishing this chapter.");
                return false;
            }
        }

//        if (chapter.completeChapter()) {
//            chapter.recruitRook(party);
//        }

        completed = true;
        System.out.println("Chapter completed: " + title);
        return true;
    }

    public void displayChapter() {
        System.out.println("\n=== Chapter "
                + chapterNumber + ": " + title + " ===");
        System.out.println(story);
        System.out.println("Status: "
                + (completed ? "Completed"
                : unlocked ? "Unlocked" : "Locked"));
        System.out.println("Quests: " + bountyList.size());
    }

//    public void recruitRook(Party party) {
//        if (party == null) {
//            return;
//        }
//
//        if (completed && !party.hasMember("Rook")) {
//            party.addMember(new Gunner("Rook"));
//            System.out.println("Rook has joined your journey!");
//        }
//    }
}
