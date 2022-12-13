import {atom} from 'recoil';

const userStudentId = atom({
  key: 'userStudentId',
  default: '',
});

export default userStudentId;
