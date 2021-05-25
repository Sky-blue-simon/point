# point

## 아마존 컨테이너 레지스트리
  - 아마존 > ecr (elastic container registry) > ecr repository : ECR은 각 배포될 이미지 대상과 이름을 맞춰준다

    aws ecr create-repository --repository-name teamtwohotel --region ap-northeast-2

    aws ecr put-image-scanning-configuration --repository-name teamtwohotel --image-scanning-configuration scanOnPush=true --region ap-northeast-2




