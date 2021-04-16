package org.improving.entity;

import lombok.Data;

import java.util.Objects;

@Data
public class ArchiveResource
{
   private String title;
   private String link;

   public ArchiveResource()
   {
   }

   public ArchiveResource( String title,
                           String link )
   {
      this.title = title;
      this.link = link;
   }

   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      ArchiveResource resource = (ArchiveResource) o;
      return title.equals(resource.title) && link.equals(resource.link);
   }

   @Override
   public int hashCode() {
      return Objects.hash(title, link);
   }
}
