import React from "react";
import "../css/NewsBox.css";

function NewsBox({
  title,
  description,
  imageUrl,
  sourceUrl,
  source,
  author,
  summarize,
}) {
  return (
    <div className="news-box">
      <div className="news-image-container">
        <img className="news-image" src={imageUrl} alt="news" />
      </div>
      <div className="news-content-container">
        <h3 className="news-title">{title}</h3>
        <p className="news-description">{description}</p>
        <p>
          <b>Source:</b> {source}
        </p>
        <p>
          <b>Author:</b> {author}
        </p>
        <div className="news-buttons">
          <button
            className="view-button"
            onClick={() => {
              window.open(sourceUrl, "_blank");
            }}
          >
            View
          </button>
          <button
            className="summary-button"
            onClick={() => {
              summarize();
            }}
          >
            Summarize by AI
          </button>
        </div>
      </div>
    </div>
  );
}

export default NewsBox;
