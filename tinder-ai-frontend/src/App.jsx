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

function App() {
  return (
    <>
      <div className="max-w-md mx-auto">
        <nav className="flex justify-between">
          <User />
          <MessageCircle />
        </nav>
        <ProfileSelector />
      </div>
    </>
  );
}

export default App;
