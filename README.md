# point

## 아마존 컨테이너 레지스트리
  - 아마존 > ecr (elastic container registry) > ecr repository : AWS 컨테이너 레지스트리에 이미지 리파지토리 생성

    aws ecr create-repository --repository-name lecture-point --region ap-northeast-2

    aws ecr put-image-scanning-configuration --repository-name lecture-point --image-scanning-configuration scanOnPush=true --region ap-northeast-2
    
  - AWS 컨테이너 레지스트리 로그인
  
    aws ecr get-login-password --region (Region-Code) | docker login --username AWS --password-stdin (Account-Id).dkr.ecr.(Region-Code).amazonaws.com
    
    aws ecr get-login-password --region ap-northeast-2 | docker login --username AWS --password-stdin 052937454741.dkr.ecr.ap-northeast-2.amazonaws.com

    오류(unknown flag: --password-stdin) 발생 시,
      
      docker login --username AWS -p $(aws ecr get-login-password --region (Region-Code)) (Account-Id).dkr.ecr.(Region-Code).amazonaws.com/
      
      docker login --username AWS -p $(aws ecr get-login-password --region ap-northeast-2) 052937454741.dkr.ecr.ap-northeast-2.amazonaws.com/
      
  - AWS 레지스트리에 도커 이미지 푸시하기
    
    aws ecr create-repository --repository-name lecture-point --region ap-northeast-2
    
    docker push 052937454741.dkr.ecr.ap-northeast-2.amazonaws.com/lecture-point:latest



    
      
      




