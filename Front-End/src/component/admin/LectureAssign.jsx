import React, { useState, useEffect, useMemo } from 'react';
import { Button, Select } from 'antd';
import useGetAllLectures from '../../hooks/lecture/useGetAllLectures';
import useAssignLecture from '../../hooks/admin/useAssignLecture';
import { toast } from 'react-toastify';
import useGetPermissionOfCompetition from '../../hooks/admin/useGetPermissionOfCompetition';
import useRemoveLecture from '../../hooks/admin/useRemoveLecture';

const LectureAssign = ({
  selectedCompetition,
  setSelectedCompetition,
  open,
}) => {
  const { data: lectures } = useGetAllLectures();
  const { data: permissions } = useGetPermissionOfCompetition(
    selectedCompetition?.id
  );

  const assignLecture = useAssignLecture();
  const removeLecture = useRemoveLecture();

  const [selectedLectures, setSelectedLectures] = useState([]);

  // const defaultValues = permissions?.data?.map(
  //   (lecture) => lecture.giangVienId
  // );

  const defaultValues = useMemo(() => {
    return permissions?.data?.map((lecture) => lecture.giangVienId) || [];
  }, [permissions]);

  // useEffect(() => {
  //   if (
  //     defaultValues &&
  //     defaultValues.length > 0 &&
  //     selectedLectures.length === 0
  //   ) {
  //     setSelectedLectures(defaultValues);
  //   }
  // }, [defaultValues, selectedLectures.length]);

  useEffect(() => {
    setSelectedLectures(defaultValues || []);
  }, [defaultValues, selectedCompetition]);

  const options = lectures?.data?.map((lecture) => ({
    label: `${lecture.tenGiangVien} - ${lecture.maGiangVien}`,
    value: lecture.maGiangVien,
  }));

  const handleChange = (value) => {
    setSelectedLectures(value);
  };

  const handleSubmit = async () => {
    if (!permissions?.data) return;

    for (const lecture of permissions.data) {
      await removeLecture.mutateAsync({
        competitionId: lecture.cuocThiId,
        lectureId: lecture.giangVienId,
      });
    }

    for (const lecture of selectedLectures) {
      await assignLecture.mutateAsync({
        competitionId: selectedCompetition.id,
        lectureId: lecture,
      });
    }

    toast.success('Cấp quyền thành công');
  };

  // console.log('check selectedLectures', selectedLectures);
  console.log('check permissions', permissions?.data);

  return (
    <>
      <Select
        mode='multiple'
        style={{ width: '100%' }}
        placeholder='Chọn giảng viên'
        value={selectedLectures}
        onChange={handleChange}
        options={options}
        showSearch
        filterOption={(input, option) =>
          option.label.toLowerCase().includes(input.toLowerCase())
        }
      />
      <Button type='primary' style={{ marginTop: 20 }} onClick={handleSubmit}>
        Xác nhận
      </Button>
    </>
  );
};

export default LectureAssign;
