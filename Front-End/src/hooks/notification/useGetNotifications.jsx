import { useQuery } from '@tanstack/react-query';
import React from 'react';
import { notificationApi } from '../../api/notificationApi';

function useGetNotifications() {
  return useQuery({
    queryKey: ['notifications'],
    queryFn: () => notificationApi.getAll(),
  });
}

export default useGetNotifications;
