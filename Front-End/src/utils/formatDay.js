import dayjs from 'dayjs';

export const formatDate = (date) => {
  const formatBy = 'DD/MM/YYYY';

  return dayjs(date).format(formatBy);
};

export const formatDateString = (date) => {
  const formattedDate = `${date.getFullYear()}-${String(
    date.getMonth() + 1
  ).padStart(2, '0')}-${String(date.getDate()).padStart(2, '0')}`;

  return formattedDate;
};
