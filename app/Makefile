checkstyle:
	./gradlew checkstyleTest
	./gradlew checkstyleMain

test:
	./gradlew test

.PHONY: build
build: checkstyle
	./gradlew test
	./gradlew build

test-installDist:
	./gradlew installDist
	build/install/app/bin/app -h