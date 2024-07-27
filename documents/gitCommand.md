* 현재 상태 확인
```bash 
git status
```

* 전체 로그 확인
```bash 
git log
```

* git 저장소 생성하기
```bash 
git init
```

* 저장소 복제 및 다운로드
```bash 
git clone [https://~]
```

* 저장소에 코드 추가
```bash 
git add 

git add *
```

* 커밋에 파일의 변경 사항을 한번에 모두 포함
```bash 
git add -A
```

* 커밋 생성
```bash 
git commit -m "message"
```

* 변경 사항 원격 서버 업로드 (push)
```bash 
git push origin master
```

* 원격 저장소의 변경 내용을 현재 디렉토리로 가져오기 (pull)
```bash 
git pull
```

* 변경 내용을 merge 하기 전에 바뀐 내용 비교
```bash 
git diff [브랜치 이름] [다른 브랜치 이름]
```