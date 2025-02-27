import React, { useState, useEffect } from 'react';
import { Tabs, Button, Popconfirm, Space } from 'antd';
import { CloseOutlined } from '@ant-design/icons';
import CompetitionRoundForm from './CompetitionRoundForm';
import useGetRounds from '../../hooks/competition/useGetRounds';
import { useParams } from 'react-router-dom';
import useDeleteRound from '../../hooks/competition/useDeleteRound'; // assuming you have a custom hook for deleting rounds
import { toast } from 'react-toastify';
import { useQueryClient } from '@tanstack/react-query';

const CompetitionRound = () => {
  const { id: competitionId } = useParams();
  const { data: rounds } = useGetRounds(competitionId || '');
  const { mutateAsync: deleteRound } = useDeleteRound(); // Delete round mutation

  const [tabs, setTabs] = useState([]);
  const [roundCount, setRoundCount] = useState(0);
  const [shouldUpdate, setShouldUpdate] = useState(false);

  const queryClient = useQueryClient();

  // Only run the effect when rounds data changes
  useEffect(() => {
    if (rounds?.data?.length) {
      const updatedTabs = rounds.data.map((round, index) => ({
        label: (
          <Space>
            {`Vòng ${index + 1}`}
            <Popconfirm
              title='Bạn có chắc muốn xóa vòng thi này?'
              onConfirm={() => handleDeleteRound(round, index)} // Call delete on confirm
              okText='Yes'
              cancelText='No'
            >
              <CloseOutlined style={{ color: 'red', cursor: 'pointer' }} />
            </Popconfirm>
          </Space>
        ),
        key: String(index + 1),
        children: <CompetitionRoundForm round={round} />,
      }));

      setTabs(updatedTabs);
      setRoundCount(rounds.data.length);
    }
  }, [rounds?.data]); // Only trigger if rounds data changes

  // Handle delete round
  const handleDeleteRound = async (round, index) => {
    console.log('check round', round);

    // Call the mutation to delete the round
    await deleteRound({
      competitionId: round.cuocThiId,
      roundId: round.maVongThi,
    });

    // Remove the deleted tab
    setTabs((prevTabs) =>
      prevTabs.filter((tab) => tab.key !== String(index + 1))
    );
    setRoundCount((prevCount) => prevCount - 1); // Update round count

    toast.success('Xóa thành công');

    queryClient.invalidateQueries(['rounds', competitionId]);
  };

  // Add a new round
  const addNewRound = () => {
    const newRound = roundCount + 1;
    setRoundCount(newRound);

    setTabs((prevTabs) => [
      ...prevTabs,
      {
        label: `Vòng ${newRound}`,
        key: String(newRound),
        children: <CompetitionRoundForm />,
      },
    ]);
  };

  return (
    <>
      <Button
        type='primary'
        onClick={addNewRound}
        style={{ marginBottom: 16, backgroundColor: '#03ae3a' }}
      >
        Thêm Vòng Mới
      </Button>
      <Tabs type='card' items={tabs} />
    </>
  );
};

export default CompetitionRound;
