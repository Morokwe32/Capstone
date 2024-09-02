package za.ac.cput.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDateTime;
import java.util.Objects;

/**HelpDesk.java
 * Domain class for Help Desk page
 * Author: Moegamat Behardien (219233829)
 * Date: 01/03/2024
 * */

@Entity
public class HelpDesk
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String category;
    private String title;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public HelpDesk() {
    }

    public HelpDesk(Builder builder) {
        this.id = builder.id;
        this.category = builder.category;
        this.title = builder.title;
        this.content = builder.content;
        this.createdAt = builder.createdAt;
        this.updatedAt = builder.updatedAt;
    }

    public static Builder builder() {
        return new Builder();
    }

    public int getId() {
        return id;
    }

    public String getCategory() {
        return category;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HelpDesk that = (HelpDesk) o;
        return id == that.id && Objects.equals(category, that.category) && Objects.equals(title, that.title) && Objects.equals(content, that.content) && Objects.equals(createdAt, that.createdAt) && Objects.equals(updatedAt, that.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, category, title, content, createdAt, updatedAt);
    }

    @Override
    public String toString() {
        return "HelpCenter{" +
                "id=" + id +
                ", category='" + category + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }

    public static class Builder {
        private int id;
        private String category;
        private String title;
        private String content;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;

        public Builder setId(int id) {
            this.id = id;
            return this;
        }

        public Builder setCategory(String category) {
            this.category = category;
            return this;
        }

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder setContent(String content) {
            this.content = content;
            return this;
        }

        public Builder setCreatedAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public Builder setUpdatedAt(LocalDateTime updatedAt) {
            this.updatedAt = updatedAt;
            return this;
        }

        public Builder copy(HelpDesk helpDesk) {
            this.id = helpDesk.id;
            this.category = helpDesk.category;
            this.title = helpDesk.title;
            this.content = helpDesk.content;
            this.createdAt = helpDesk.createdAt;
            this.updatedAt = helpDesk.updatedAt;
            return this;
        }

        public HelpDesk build() {
            return new HelpDesk(this);
        }
    }
}
