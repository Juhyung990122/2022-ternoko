import * as S from './styled';

import ErrorMessage from '@/components/@common/ErrorMessage/styled';

export type TextAreaFieldProps = {
  id: string;
  label: string;
  value: string;
  isSubmitted: boolean;
  handleChange: (e: React.ChangeEvent<HTMLTextAreaElement>) => void;
  checkValidation: (text: string) => boolean;
};

const TextAreaField = ({
  id,
  label,
  value,
  isSubmitted,
  handleChange,
  checkValidation,
}: TextAreaFieldProps) => {
  return (
    <S.Box>
      <S.Label htmlFor={id}>{label}</S.Label>
      <S.TextArea
        id={id}
        value={value}
        isError={isSubmitted && !checkValidation(value)}
        onChange={handleChange}
      />
      {isSubmitted && !checkValidation(value) && (
        <ErrorMessage>10글자 이상을 입력해주세요.</ErrorMessage>
      )}
    </S.Box>
  );
};

export default TextAreaField;
