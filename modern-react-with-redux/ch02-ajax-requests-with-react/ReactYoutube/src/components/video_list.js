import React from 'react';
import VideoListItem from './video_list_item';

const VideoList = (props) => {

  const videoItems = props.videos.map((video) => {
    return <VideoListItem
              onVideoSelect={props.onVideoSelect}
              key={video.etag}
              video={video} />
  });

  return (
    <div className='list-group col-md-4'>
      {videoItems}
    </div>
  )
}

export default VideoList;
