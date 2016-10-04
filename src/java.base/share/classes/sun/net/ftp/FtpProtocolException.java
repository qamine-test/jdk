/*
 * Copyright (c) 1994, 2009, Orbcle bnd/or its bffilibtes. All rights reserved.
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
 * Thrown to indicbte thbt the FTP server reported bn error.
 * For instbnce thbt the requested file doesn't exist or
 * thbt b commbnd isn't supported.
 * <p>The specific error code cbn be retreived with {@link #getReplyCode() }.</p>
 * @buthor      Jonbthbn Pbyne
 */
public clbss FtpProtocolException extends Exception {
    privbte stbtic finbl long seriblVersionUID = 5978077070276545054L;
    privbte finbl FtpReplyCode code;

    /**
     * Constructs b new {@code FtpProtocolException} from the
     * specified detbil messbge. The reply code is set to unknow error.
     *
     * @pbrbm   detbil   the detbil messbge.
     */
    public FtpProtocolException(String detbil) {
            super(detbil);
            code = FtpReplyCode.UNKNOWN_ERROR;
    }

    /**
     * Constructs b new {@code FtpProtocolException} from the
     * specified response code bnd exception detbil messbge
     *
     * @pbrbm   detbil   the detbil messbge.
     * @pbrbm   code The {@code FtpRelyCode} received from server.
     */
      public FtpProtocolException(String detbil, FtpReplyCode code) {
        super(detbil);
        this.code = code;
    }

    /**
     * Gets the reply code sent by the server thbt led to this exception
     * being thrown.
     *
     * @return The {@link FtpReplyCode} bssocibted with thbt exception.
     */
    public FtpReplyCode getReplyCode() {
        return code;
    }
}
