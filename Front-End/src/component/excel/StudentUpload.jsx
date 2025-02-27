import React from 'react';
import { InboxOutlined } from '@ant-design/icons';
import { message, Upload } from 'antd';
const { Dragger } = Upload;
const props = {
  name: 'file',
  multiple: true,
  action: 'http://localhost:8080/api/sinhvien/upload-danhsach',
  onChange(info) {
    const { status } = info.file;
    if (status !== 'uploading') {
      console.log(info.file, info.fileList);
    }
    if (status === 'done') {
      message.success(`File: ${info.file.name} đã được upload thành công.`);
    } else if (status === 'error') {
      message.error(`File: ${info.file.name} upload thất bại`);
    }
  },
  onDrop(e) {
    console.log('Dropped files', e.dataTransfer.files);
  },
};
const LectureUpload = () => (
  <Dragger {...props}>
    <p className='ant-upload-drag-icon'>
      <InboxOutlined />
    </p>
    <p className='ant-upload-text'>Nhấn hoặc kéo thả để upload</p>
  </Dragger>
);
export default LectureUpload;
