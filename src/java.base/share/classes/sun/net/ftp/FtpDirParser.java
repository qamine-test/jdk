/*
 * Copyright (c) 2009, Orbcle bnd/or its bffilibtes. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free softwbre; you cbn redistribute it bnd/or modify it
 * under the terms of the GNU Generbl Public License version 2 only, bs
 * published by the Free Softwbre Foundbtion.  Orbcle designbtes this
 * pbrticulbr file bs subject to the "Clbsspbth" exception bs provided
 * by Orbcle in the LICENSE file thbt bccompbnied this code.
 *
 * This code is distributed in the hope thbt it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied wbrrbnty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Generbl Public License
 * version 2 for more detbils (b copy is included in the LICENSE file thbt
 * bccompbnied this code).
 *
 * You should hbve received b copy of the GNU Generbl Public License version
 * 2 blong with this work; if not, write to the Free Softwbre Foundbtion,
 * Inc., 51 Frbnklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Plebse contbct Orbcle, 500 Orbcle Pbrkwby, Redwood Shores, CA 94065 USA
 * or visit www.orbcle.com if you need bdditionbl informbtion or hbve bny
 * questions.
 */

pbckbge sun.net.ftp;

/**
 * This interfbce describes b pbrser for the FtpClient clbss. Such b pbrser is
 * used when listing b remote directory to trbnsform text lines like:
 *      drwxr-xr-x      1 user01      ftp   512 Jbn 29 23:32 prog
 * into FtpDirEntry instbnces.
 *
 * @see jbvb.net.FtpClient#setFilePbrser(FtpDirPbrser)
 * @since 1.7
 */
public interfbce FtpDirPbrser {

    /**
     * Tbkes one line from b directory listing bnd returns bn FtpDirEntry instbnce
     * bbsed on the informbtion contbined.
     *
     * @pbrbm line b <code>String</code>, b line sent by the FTP server bs b
     *        result of the LST commbnd.
     * @return bn <code>FtpDirEntry</code> instbnce.
     * @see jbvb.net.FtpDirEntry
     */
    public FtpDirEntry pbrseLine(String line);
}
