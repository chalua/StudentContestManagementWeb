import React from 'react';
import Modal from 'antd/es/modal/Modal';
import { Button } from 'antd';

const ScoreModal = ({ show, handleClose, score }) => {
    return (
        <Modal open={show} onCancel={handleClose} footer={null}>
            {/* <Modal.Header closeButton>
                <Modal.Title>Thông tin điểm số</Modal.Title>
            </Modal.Header> */}
            {/* <Modal.Body>
                <p>Tên nhóm: {score.tenNhom}</p>
                <p>Thời gian chấm: {score.thoiGianCham}</p>
                <p>Người chấm: {score.nguoiCham}</p>
            </Modal.Body> */}
            <h2>Thông tin điểm số</h2>
            <Button type="primary" onClick={handleClose}>
                Đóng
            </Button>
        </Modal>
    );
}

export default ScoreModal;