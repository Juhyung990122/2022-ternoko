import CoachProfile, { CoachProfileProps } from '.';

import { Story } from '@storybook/react';

export default {
  title: 'components/CoachProfile',
  component: CoachProfile,
};

const Template = (args: CoachProfileProps) => <CoachProfile {...args} />;

export const Basic: Story<CoachProfileProps> = Template.bind({});

Basic.args = {
  id: 1,
  currentCoachId: 2,
  nickname: '브리',
  imageUrl:
    'https://user-images.githubusercontent.com/43205258/177765431-63d39896-c8e1-42e8-a229-08309849f2ff.png',
  getHandleClickProfile: () => () => {},
};
