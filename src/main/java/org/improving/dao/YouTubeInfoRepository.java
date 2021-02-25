package org.improving.dao;

import org.improving.entity.YouTubeInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin( "http://localhost:4200")
@RepositoryRestResource( collectionResourceRel = "youTubeInfo", path = "youtube-info" )
public interface YouTubeInfoRepository extends JpaRepository< YouTubeInfo, Integer >
{
}
