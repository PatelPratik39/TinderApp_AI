import { User, MessageCircle, X, Heart } from "lucide-react";
import "./App.css";

const ProfileSelector = () => (
  <div className="rounded-lg overflow-hidden bg-white shadow-lg">
    <div className="relative">
      <img src="http://127.0.0.1:8081/018aafd0-6a0d-4f38-b2ca-45c0c9b13fa2.jpg" />
      <div className="absolute bottom-0 left-0 right-0 text-white p-4 bg-gradient-to-t from-black">
        <h2 className="text-3xl font-bold text-left">Foo bar, 30</h2>
      </div>
    </div>
    <div className="p-4">
      <p className="text-gray-600 ">I am a Nurse Practitioner</p>
    </div>
    <div className="p-4 flex justify-center space-x-5">
      <button
        className="bg-red-500 rounded-full p-4 text-white hover:bg-red-700"
        onClick={() => console.log("left")}
      >
        <X size={24} />
      </button>
      <button
        className="bg-green-500 rounded-full p-4 text-white hover:bg-green-700"
        onClick={() => console.log("right")}
      >
        <Heart size={24} />
      </button>
    </div>
  </div>
);

const MatchesList = () => (
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
          <button className="w-full rounded flex item-center hover:bg-gray-100">
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

function App() {
  return (
    <>
      <div className="max-w-md mx-auto p-4">
        <nav className="flex justify-between mb-4">
          <User />
          <MessageCircle />
        </nav>
        <ProfileSelector />
        <MatchesList />
      </div>
    </>
  );
}

export default App;
