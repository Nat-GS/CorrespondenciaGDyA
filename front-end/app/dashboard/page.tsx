"use client";

import { useState } from "react";
import MySideBar from "../components/MySideBar";
import RegistroCorrespondencia from "../components/RegistroCorrespondencia";
import Monitoreo from "../components/Monitoreo";
import CorrespondenciaPorRevisar from "../components/CorrespondenciaPorRevisar";

export default function Dashboard() {
  const [selectedOption, setSelectedOption] = useState("registro");

  const renderContent = () => {
    switch (selectedOption) {
      case "registro":
        return <RegistroCorrespondencia />;
      case "monitoreo":
        return <Monitoreo />;
      case "correspondencia":
          return <CorrespondenciaPorRevisar />;
      default:
        return <RegistroCorrespondencia />;
    }
  };

  return (
    <div style={{ display: "flex" }}>
      <MySideBar onSelect={setSelectedOption} />
      <div style={{ padding: "1rem", width: "100%" }}>{renderContent()}</div>
    </div>
  );
}
