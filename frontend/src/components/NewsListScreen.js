import React, { useEffect, useState } from "react";
import axios from "axios";
import NewsBox from "./NewsBox";
import "../css/NewsListScreen.css";
import Typewriter from "typewriter-effect";

function NewsListScreen({ onBack, newsCategory }) {
  const [news, setnews] = React.useState([]);
  const [summary, setSummary] = useState("");
  const [showSummary, setShowSummary] = useState(false);
  const [loading, setLoading] = useState(false);

  const useTypewriter = (text, speed = 10) => {
    const [displayText, setDisplayText] = useState("");
    useEffect(() => {
      setDisplayText("");
      let i = -1;

      const typeCharacter = () => {
        if (i < text.length) {
          setDisplayText((prevText) => prevText + text.charAt(i));
          setTimeout(typeCharacter, speed);
        }
        i++;
      };

      typeCharacter();
    }, [text, speed]);

    return displayText;
  };

  useEffect(() => {
    axios
      .get("http://localhost:8080/api/category", {
        params: {
          category: newsCategory,
        },
      })
      .then((res) => {
        setnews(res.data.articles);
      })
      .catch((e) => alert(e));
  }, []);

  const summarize = async (url) => {
    try {
      setSummary("");
      console.log("clicked button with url: " + url);
      setLoading(true);
      setShowSummary(true);
      const res = await axios.get("http://localhost:8080/api/summary", {
        params: { url },
      });
      setSummary(res.data || "Summarizing...");
      console.log("Got data: " + res.data);
    } catch (e) {
      setSummary("Error fetching summary.");
      console.log("Error: " + e);
    } finally {
      setLoading(false);
    }
  };

  const text = useTypewriter(summary, 10);

  return (
    <div className="content">
      <h2 style={{ color: "#0ff", textShadow: "0 0 10px #0ff" }}>
        {newsCategory} News
      </h2>

      {showSummary && (
        <div id="overlay">
          <div id="summary-box">
            <h4> AI Summary:</h4>
            {loading ? <p>Summarizing...</p> : <p id="summary-text">{text}</p>}
          </div>
          <button onClick={() => setShowSummary(false)}>Close</button>
        </div>
      )}
      <br />
      <br />
      <button onClick={onBack}>Back</button>
      <br />
      <br />
      {news &&
        news.map((item, idx) => (
          <NewsBox
            key={item.url}
            title={item.title}
            description={item.description}
            imageUrl={item.urlToImage}
            sourceUrl={item.url}
            source={item.source.name}
            author={item.author}
            summarize={() => summarize(item.url)}
          />
        ))}
    </div>
  );
}

export default NewsListScreen;
