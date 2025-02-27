import React, { useEffect, useState } from 'react';
import { Select } from 'antd';
import useGetGiaiThuong from '../../hooks/lecture/useGetGiaiThuong';
import useGoal from '../../hooks/lecture/useGoal';
import { toast } from 'react-toastify';
import useGetGoal from '../../hooks/lecture/useGetGoal';
import { useQueryClient } from '@tanstack/react-query';

const GoalSelect = ({ selectedTeam }) => {
  const { data: allGiaiThuong } = useGetGiaiThuong();
  const { data: goalData } = useGetGoal(selectedTeam?.maNhom);
  const queryClient = useQueryClient();

  const goal = useGoal();

  const [defaultGoal, setDefaultGoal] = useState(null);

  useEffect(() => {
    if (goalData) {
      setDefaultGoal(goalData?.data?.maGiaiThuong);
    }
  }, [goalData]);

  const onChange = async (value) => {
    console.log(`selected ${value} ${selectedTeam.maNhom}`);

    await goal.mutateAsync({
      maNhomSinhVien: selectedTeam.maNhom,
      maGiaiThuong: value,
    });

    toast.success('Cấp giải thành công');

    queryClient.invalidateQueries({});
  };

  const onSearch = (value) => {
    console.log('search:', value);
  };

  const options =
    allGiaiThuong?.data?.map((gt) => {
      return {
        value: gt.maGiaiThuong,
        label: gt.tenGiaiThuong,
      };
    }) || [];

  return (
    <Select
      showSearch
      placeholder='Chọn giải thưởng'
      optionFilterProp='label'
      onChange={onChange}
      onSearch={onSearch}
      options={[{ label: 'Chọn giải', value: null }, ...options]}
      value={defaultGoal}
    />
  );
};

export default GoalSelect;
