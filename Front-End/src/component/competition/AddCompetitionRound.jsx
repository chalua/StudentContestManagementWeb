import { Button, Flex, InputNumber } from 'antd';
import React from 'react';

function AddCompetitionRound(props) {
  const onChange = (value) => {
    console.log('changed', value);
  };

  return (
    <Flex gap={2}>
      <InputNumber min={1} max={10} defaultValue={3} onChange={onChange} />;
      <Button type='primary'>Tạo vòng thi</Button>
    </Flex>
  );
}

export default AddCompetitionRound;
