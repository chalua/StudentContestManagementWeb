import React from 'react';
import { useParams } from 'react-router-dom';
import useGetNotificationDetail from '../../hooks/notification/useGetNotificationDetail';
import { Button } from 'antd';
import { Document, Page, pdfjs } from 'react-pdf';

pdfjs.workerSrc = `https://cdnjs.cloudflare.com/ajax/libs/pdf.js/${pdfjs.version}/pdf.worker.min.js`;
function NotificationDetail() {
  const { id } = useParams();
  const { data } = useGetNotificationDetail(id);

  const notification = data?.data;
  const fileBase64 = notification?.fileBase64;

  const base64ToBlob = (base64, mime) => {
    const byteCharacters = atob(base64);
    const byteArrays = [];

    for (let offset = 0; offset < byteCharacters.length; offset += 512) {
      const slice = byteCharacters.slice(offset, offset + 512);
      const byteNumbers = new Array(slice.length);

      for (let i = 0; i < slice.length; i++) {
        byteNumbers[i] = slice.charCodeAt(i);
      }

      byteArrays.push(new Uint8Array(byteNumbers));
    }

    return new Blob(byteArrays, { type: mime });
  };

  const handleDownload = () => {
    const blob = base64ToBlob(fileBase64, 'application/pdf');
    const url = URL.createObjectURL(blob);
    const link = document.createElement('a');
    link.href = url;
    link.download = 'file_tai_xuong.pdf';
    link.click();
  };

  console.log('check 64', fileBase64);

  return (
    <div>
      <h1 style={{ marginBottom: '30px' }}>{notification?.tieuDe}</h1>

      <div dangerouslySetInnerHTML={{ __html: notification?.noiDung }}></div>

      {/* {fileBase64 && (
        <iframe
          src={`data:application/pdf;base64,${fileBase64}`}
          width='100%'
          height='600px'
          title='PDF File'
        />
      )} */}

      {fileBase64 && <Button onClick={handleDownload}>Tải tệp đính kèm</Button>}
    </div>
  );
}

export default NotificationDetail;
