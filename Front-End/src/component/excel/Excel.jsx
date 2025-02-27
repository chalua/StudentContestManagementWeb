import { Col, Divider, Row } from 'antd';
import React from 'react';
import StudentUpload from './StudentUpload';
import LectureUpload from './LectureUpload';

function Excel() {
  return (
    <>
      <h1 style={{ textAlign: 'center' }}>Tải lên Sinh Viên</h1>
      <StudentUpload />

      <Divider />

      <h1 style={{ textAlign: 'center' }}>Tải lên Giảng Viên</h1>
      <LectureUpload />
    </>
  );
}

export default Excel;
