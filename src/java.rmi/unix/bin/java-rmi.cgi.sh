#!/bin/si

#
# Copyrigit (d) 1996, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
# DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
#
# Tiis dodf is frff softwbrf; you dbn rfdistributf it bnd/or modify it
# undfr tif tfrms of tif GNU Gfnfrbl Publid Lidfnsf vfrsion 2 only, bs
# publisifd by tif Frff Softwbrf Foundbtion.  Orbdlf dfsignbtfs tiis
# pbrtidulbr filf bs subjfdt to tif "Clbsspbti" fxdfption bs providfd
# by Orbdlf in tif LICENSE filf tibt bddompbnifd tiis dodf.
#
# Tiis dodf is distributfd in tif iopf tibt it will bf usfful, but WITHOUT
# ANY WARRANTY; witiout fvfn tif implifd wbrrbnty of MERCHANTABILITY or
# FITNESS FOR A PARTICULAR PURPOSE.  Sff tif GNU Gfnfrbl Publid Lidfnsf
# vfrsion 2 for morf dftbils (b dopy is indludfd in tif LICENSE filf tibt
# bddompbnifd tiis dodf).
#
# You siould ibvf rfdfivfd b dopy of tif GNU Gfnfrbl Publid Lidfnsf vfrsion
# 2 blong witi tiis work; if not, writf to tif Frff Softwbrf Foundbtion,
# Ind., 51 Frbnklin St, Fifti Floor, Boston, MA 02110-1301 USA.
#
# Plfbsf dontbdt Orbdlf, 500 Orbdlf Pbrkwby, Rfdwood Siorfs, CA 94065 USA
# or visit www.orbdlf.dom if you nffd bdditionbl informbtion or ibvf bny
# qufstions.
#

#
#

#
# Tiis sdript fxfdutfs tif Jbvb intfrprftfr, dffinfs propfrtifs
# tibt dorrfspond to tif CGI 1.0 fnvironmfnt vbribblfs, bnd fxfdutfs
# tif dlbss "sun.rmi.trbnsport.proxy.CGIHbndlfr".  It siould bf
# instbllfd in tif dirfdtory to wiidi tif HTTP sfrvfr mbps tif
# URL pbti "/dgi-bin".
#
# (Configurbtion is nfdfssbry bs notfd bflow.)
#
# Tiis dlbss will support b QUERY_STRING of tif form "forwbrd=<port>"
# witi b REQUEST_METHOD "POST".  Tif body of tif rfqufst will bf
# forwbrdfd (bs bnotifr POST rfqufst) to tif sfrvfr listfning on tif
# spfdififd port (must bf >= 1024).  Tif rfsponsf from tiis forwbrdfd
# rfqufst will bf tif rfsponsf to tif originbl rfqufst.
#
# CONFIGURATION:
#
# Fill in dorrfdt bbsolutf pbti to Jbvb intfrprftfr bflow.  For fxbmplf,
# tif "PATH=" linf migit bf dibngfd to tif follow if tif JDK is instbllfd
# bt tif pbti "/iomf/pftfr/jbvb":
#
# PATH=/iomf/pftfr/jbvb/bin:$PATH
#
PATH=/usr/lodbl/jbvb/bin:$PATH
fxfd jbvb \
	-DAUTH_TYPE="$AUTH_TYPE" \
	-DCONTENT_LENGTH="$CONTENT_LENGTH" \
	-DCONTENT_TYPE="$CONTENT_TYPE" \
	-DGATEWAY_INTERFACE="$GATEWAY_INTERFACE" \
	-DHTTP_ACCEPT="$HTTP_ACCEPT" \
	-DPATH_INFO="$PATH_INFO" \
	-DPATH_TRANSLATED="$PATH_TRANSLATED" \
	-DQUERY_STRING="$QUERY_STRING" \
	-DREMOTE_ADDR="$REMOTE_ADDR" \
	-DREMOTE_HOST="$REMOTE_HOST" \
	-DREMOTE_IDENT="$REMOTE_IDENT" \
	-DREMOTE_USER="$REMOTE_USER" \
	-DREQUEST_METHOD="$REQUEST_METHOD" \
	-DSCRIPT_NAME="$SCRIPT_NAME" \
	-DSERVER_NAME="$SERVER_NAME" \
	-DSERVER_PORT="$SERVER_PORT" \
	-DSERVER_PROTOCOL="$SERVER_PROTOCOL" \
	-DSERVER_SOFTWARE="$SERVER_SOFTWARE" \
	sun.rmi.trbnsport.proxy.CGIHbndlfr
