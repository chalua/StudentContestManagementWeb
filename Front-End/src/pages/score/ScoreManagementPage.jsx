import React, { useState } from 'react';
import useCompetition from '@globalstate/UseCompetition';
import CompetitionSelect from '@component/score/CompetitionSelect';
import { Layout, List } from 'antd';
import { Content, Header } from 'antd/es/layout/layout';
import ScoreManagementTable from '@component/score/ScoreManagementTable';
import ScoreModal from '@component/score/ScoreModel';

const ScoreManagementPage = () => {
  const { selectedCompetition, competitionData, setSelectedCompetition } = useCompetition();

  const [isModalVisible, setIsModalVisible] = useState(false);
  const [selectedScore, setSelectedScore] = useState(null);

  const ongoingRounds = selectedCompetition?.vongThi.filter(vong => vong.trangThai === 'Đang diễn ra') || [];

  const handleButtonClick = (score) => {
    setSelectedScore(score);
    setIsModalVisible(true);
    console.log(score);
    console.log(isModalVisible);
  };

  const handleCloseModal = () => {
    setIsModalVisible(false);
    setSelectedScore(null);
  };

  return (
    <>
        <Layout>
          <Header 
            style={{ background: '#fff', padding: '0 24px' }}
          >
            <CompetitionSelect 
              competition={competitionData} 
              setCompetition={setSelectedCompetition} 
            />
          </Header>
        <Content>
            {selectedCompetition && (
                <ScoreManagementTable 
                    scores={selectedCompetition.vongThi.filter(vong => vong.trangThai === 'Đang diễn ra')[0].ketQuaVongThi}
                    btnOnclick={handleButtonClick} 
                />
            )}
        </Content>
        </Layout>   
        <ScoreModal 
            show={isModalVisible} 
            handleClose={handleCloseModal} 
            score={selectedScore} 
        />
    </>
  );
}

export default ScoreManagementPage;