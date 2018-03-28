#!/usr/bin/env bash
#GENERATE
gpg --gen-key
gpg --edit-key bhuwan.upadhyay49@gmail.com
gpg --list-keys

#PUBLISH
gpg --send-keys --keyserver keyserver.ubuntu.com $keyid
gpg --send-keys --keyserver pgp.mit.edu $keyid
gpg --send-keys --keyserver pool.sks-keyservers.net $keyid


gpg --export --armor bhuwan.upadhyay49@gmail.com > mysupersecretkey.asc
gpg --export-secret-keys --armor bhuwan.upadhyay49@gmail.com >> mysupersecretkey.asc
shred --remove mysupersecretkey.asc
gpg --export-secret-subkeys your@email.com > subkeys
gpg --delete-secret-key your@email.com
gpg --import subkeys
shred --remove subkeys
gpg --edit-key your@email.com
gpg --list-secret-keys

gpg --edit-key your@email.com

travis login
gpg --export --armor your@email.com > codesigning.asc
gpg --export-secret-keys --armor your@email.com >> codesigning.asc
travis encrypt-file codesigning.asc
travis encrypt OSSRH_JIRA_USERNAME=super_secret
travis encrypt OSSRH_JIRA_PASSWORD=super_secret
travis encrypt GPG_KEY_NAME=super_secret
travis encrypt GPG_PASSPHRASE=super_secret

