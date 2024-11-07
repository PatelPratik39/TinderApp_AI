import { User, MessageCircle, X, Heart } from "lucide-react";
import "./App.css";
import React, { useState, useEffect } from "react";

// Utility method to call API calls
const fetchRandomProfile = async () => {
  const response = await fetch("http://localhost:8080/profiles/random");
  if (!response.ok) {
    throw new Error("Failed to fetch profile");
  }
  return response.json();
};

const saveSwipe = async (profileId) => {
  const response = await fetch("http://localhost:8080/matches", {
    method: "POST",
    headers: {
      "Content-Type": "application/json"
    },
    body: JSON.stringify({ profileId })
  });
  if (!response.ok) {
    throw new Error("Failed to save Swipe");
  }
};

const ProfileSelector = ({ profile, onSwipe }) =>
  profile ? (
    <div className="rounded-lg overflow-hidden bg-white shadow-lg">
      <div className="relative">
        <img src={"http://127.0.0.1:8081/" + profile.imageUrl} />
        <div className="absolute bottom-0 left-0 right-0 text-white p-4 bg-gradient-to-t from-black">
          <h2 className="text-3xl font-bold text-left">
            {profile.firstName} {profile.lastName}, {profile.age}
          </h2>
        </div>
      </div>
      <div className="p-4">
        <p className="text-gray-600">{profile.bio}</p>
      </div>
      <div className="p-4 flex justify-center space-x-5">
        <button
          className="bg-red-500 rounded-full p-4 text-white hover:bg-red-700"
          onClick={() => onSwipe(profile.id, "left")}
        >
          <X size={24} />
        </button>
        <button
          className="bg-green-500 rounded-full p-4 text-white hover:bg-green-700"
          onClick={() => onSwipe(profile.id, "right")}
        >
          <Heart size={24} />
        </button>
      </div>
    </div>
  ) : (
    <div>Loading...</div>
  );

const MatchesList = ({ onSelectMatch }) => (
  <div className="rounded-lg shadow-lg p-4">
    <h2 className="text-2xl font-bold mb-4 text-left"> Matches</h2>
    <ul>
      {[
        {
          id: 1,
          firstName: "Mangoo",
          lastName: "Moti",
          imageUrl:
            "http://127.0.0.1:8081/018aafd0-6a0d-4f38-b2ca-45c0c9b13fa2.jpg"
        },
        {
          id: 2,
          firstName: "Chunni",
          lastName: "Bai",
          imageUrl:
            "http://127.0.0.1:8081/017e4530-49b4-4937-8adf-985a82595d53.jpg"
        }
      ].map((match) => (
        <li key={match.id} className="mb-2">
          <button
            className="w-full rounded flex item-center hover:bg-gray-100"
            onClick={onSelectMatch}
          >
            <img
              src={match.imageUrl}
              className="w-16 h-16 rounded-full mr-3 object-cover"
            />
            <span>
              <h3 className="font-bold">
                {match.firstName} {match.lastName}
              </h3>
            </span>
          </button>
        </li>
      ))}
    </ul>
  </div>
);

const ChatScreen = () => {
  const [input, setInput] = useState("");

  const handleSend = () => {
    if (input.trim()) {
      console.log(input);
      setInput("");
    }
  };
  return (
    <div className="rounded-lg shadow-lg p-4">
      <h2 className="text-2xl font-bold mb-4">Chat with Foo Bar</h2>
      <div className="h-[50vh] border rounded overflow-y-auto mb-4 p-4">
        {["Hi !", "How are you?"].map((message, index) => (
          <div key={index}>
            <div className="mb-4 p-2 rounded bg-gray-100">{message}</div>
          </div>
        ))}
      </div>
      <div className="flex">
        <input
          type="text"
          value={input}
          onChange={(e) => setInput(e.target.value)}
          className="border flex-1 rounded p-2 mr-2"
          placeholder="Type a message...."
        />
        <button
          className="bg-green-500 text-white rounded p-2"
          onClick={handleSend}
        >
          Send
        </button>
      </div>
    </div>
  );
};

function App() {
  const loadRandomProfile = async () => {
    try {
      const profile = await fetchRandomProfile();
      setCurrentProfile(profile);
    } catch (error) {
      console.error(error);
    }
  };

  useEffect(() => {
    loadRandomProfile();
  }, []);

  const [currentScreen, setCurrentScreen] = useState("profile");
  const [currentProfile, setCurrentProfile] = useState(null);

  const onSwipe = (profileId, direction) => {
    if (direction === "right") {
      saveSwipe(profileId);
    }
    loadRandomProfile();
  };

  const renderScreen = () => {
    switch (currentScreen) {
      case "profile":
        return <ProfileSelector profile={currentProfile} onSwipe={onSwipe} />;
      case "matches":
        return <MatchesList onSelectMatch={() => setCurrentScreen("chat")} />;
      case "chat":
        return <ChatScreen />;
      default:
        break;
    }
  };

  return (
    <>
      <div className="max-w-md mx-auto p-4">
        <nav className="flex justify-between mb-4">
          <User onClick={() => setCurrentScreen("profile")} />
          <MessageCircle onClick={() => setCurrentScreen("matches")} />
        </nav>
        {renderScreen()}
        {/* <ProfileSelector /> */}
        {/* <MatchesList /> */}
      </div>
    </>
  );
}

export default App;
