def vars = (new GroovyShell()).evaluate(streamFileFromWorkspace('oi-janky-groovy/update.sh/vars.groovy'))

for (repo in vars.repos) {
	pipelineJob(repo) {
		logRotator { daysToKeep(4) }
		triggers {
			//cron('H H/6 * * *')
		}
		definition {
			cpsScm {
				scm {
					git {
						remote {
							url('https://github.com/docker-library/oi-janky-groovy.git')
						}
						extensions {
							cleanAfterCheckout()
							relativeTargetDirectory('oi-janky-groovy')
						}
					}
					scriptPath('oi-janky-groovy/update.sh/target-pipeline.groovy')
				}
			}
		}
	}
}
