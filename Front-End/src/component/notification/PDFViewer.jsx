import React, { useState, useEffect } from 'react';
import { Worker, Viewer } from '@react-pdf-viewer/core';
import '@react-pdf-viewer/core/lib/styles/index.css';

const PDFViewer = ({ base64PDF }) => {
  const [fileData, setFileData] = (useState < Uint8Array) | (null > null);

  useEffect(() => {
    if (base64PDF) {
      const byteCharacters = atob(base64PDF.split(',')[1]);
      const byteArrays = [];
      for (let offset = 0; offset < byteCharacters.length; offset += 1024) {
        const byteNumbers = new Array(1024);
        for (let i = 0; i < 1024; i++) {
          byteNumbers[i] = byteCharacters.charCodeAt(offset + i);
        }
        byteArrays.push(new Uint8Array(byteNumbers));
      }
      setFileData(new Uint8Array(byteArrays.flat()));
    }
  }, [base64PDF]);

  return fileData ? (
    <Worker
      workerUrl={`https://unpkg.com/pdfjs-dist@2.10.377/build/pdf.worker.min.js`}
    >
      <Viewer fileUrl={fileData} />
    </Worker>
  ) : (
    <div>Loading PDF...</div>
  );
};

export default PDFViewer;
