import React from "react";
import { Select } from "antd";
import useCompetition from "../../globalstate/UseCompetition";

const CompetitionSelect = ({ competition, setCompetition }) => {
    // const { competitionData, setSelectedCompetition } = useCompetition();

    const handleChange = (value) => {
      const selected = competition.find(item => item.key === value);
      setCompetition(selected);
    };
  
    return (
        <Select
            onChange={handleChange}
            style={{ width: 200 }} // Set a default width
            placeholder="Select a competition" // Add a placeholder for better UX
        >
        {competition.map((item) => (
          <Select.Option key={item.key} value={item.key}>
            {item.name}
          </Select.Option>
        ))}
      </Select>
    );
}

export default CompetitionSelect;