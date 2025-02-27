import React from 'react';
import { Select } from 'antd';
import useSpecificQuery from '../../hooks/specific/useSpecificQuery';

const ScientificSelect = () => {
  const { data } = useSpecificQuery();
  const options = data?.data.map((specific) => ({
    value: specific.id,
    label: specific.tenCuocThi,
  }));

  console.log(data);

  const onChange = (value) => {
    console.log(`selected ${value}`);
  };
  const onSearch = (value) => {
    console.log('search:', value);
  };

  return (
    <Select
      style={{ width: 200 }}
      showSearch
      placeholder='Chon cuộc thi NCKH'
      optionFilterProp='label'
      onChange={onChange}
      onSearch={onSearch}
      options={options}
    />
  );
};
export default ScientificSelect;
