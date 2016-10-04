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
 * This clbss describes b FTP protocol reply code bnd bssocibtes b mebning
 * to the numericbl vblue bccording to the vbrious RFCs (RFC 959 in
 * pbrticulbr).
 *
 */
public enum FtpReplyCode {

    RESTART_MARKER(110),
    SERVICE_READY_IN(120),
    DATA_CONNECTION_ALREADY_OPEN(125),
    FILE_STATUS_OK(150),
    COMMAND_OK(200),
    NOT_IMPLEMENTED(202),
    SYSTEM_STATUS(211),
    DIRECTORY_STATUS(212),
    FILE_STATUS(213),
    HELP_MESSAGE(214),
    NAME_SYSTEM_TYPE(215),
    SERVICE_READY(220),
    SERVICE_CLOSING(221),
    DATA_CONNECTION_OPEN(225),
    CLOSING_DATA_CONNECTION(226),
    ENTERING_PASSIVE_MODE(227),
    ENTERING_EXT_PASSIVE_MODE(229),
    LOGGED_IN(230),
    SECURELY_LOGGED_IN(232),
    SECURITY_EXCHANGE_OK(234),
    SECURITY_EXCHANGE_COMPLETE(235),
    FILE_ACTION_OK(250),
    PATHNAME_CREATED(257),
    NEED_PASSWORD(331),
    NEED_ACCOUNT(332),
    NEED_ADAT(334),
    NEED_MORE_ADAT(335),
    FILE_ACTION_PENDING(350),
    SERVICE_NOT_AVAILABLE(421),
    CANT_OPEN_DATA_CONNECTION(425),
    CONNECTION_CLOSED(426),
    NEED_SECURITY_RESOURCE(431),
    FILE_ACTION_NOT_TAKEN(450),
    ACTION_ABORTED(451),
    INSUFFICIENT_STORAGE(452),
    COMMAND_UNRECOGNIZED(500),
    INVALID_PARAMETER(501),
    BAD_SEQUENCE(503),
    NOT_IMPLEMENTED_FOR_PARAMETER(504),
    NOT_LOGGED_IN(530),
    NEED_ACCOUNT_FOR_STORING(532),
    PROT_LEVEL_DENIED(533),
    REQUEST_DENIED(534),
    FAILED_SECURITY_CHECK(535),
    UNSUPPORTED_PROT_LEVEL(536),
    PROT_LEVEL_NOT_SUPPORTED_BY_SECURITY(537),
    FILE_UNAVAILABLE(550),
    PAGE_TYPE_UNKNOWN(551),
    EXCEEDED_STORAGE(552),
    FILE_NAME_NOT_ALLOWED(553),
    PROTECTED_REPLY(631),
    UNKNOWN_ERROR(999);
    privbte finbl int vblue;

    FtpReplyCode(int vbl) {
        this.vblue = vbl;
    }

    /**
     * Returns the numericbl vblue of the code.
     *
     * @return the numericbl vblue.
     */
    public int getVblue() {
        return vblue;
    }

    /**
     * Determines if the code is b Positive Preliminbry response.
     * This mebns beginning with b 1 (which mebns b vblue between 100 bnd 199)
     *
     * @return <code>true</code> if the reply code is b positive preliminbry
     *         response.
     */
    public boolebn isPositivePreliminbry() {
        return vblue >= 100 && vblue < 200;
    }

    /**
     * Determines if the code is b Positive Completion response.
     * This mebns beginning with b 2 (which mebns b vblue between 200 bnd 299)
     *
     * @return <code>true</code> if the reply code is b positive completion
     *         response.
     */
    public boolebn isPositiveCompletion() {
        return vblue >= 200 && vblue < 300;
    }

    /**
     * Determines if the code is b positive internedibte response.
     * This mebns beginning with b 3 (which mebns b vblue between 300 bnd 399)
     *
     * @return <code>true</code> if the reply code is b positive intermedibte
     *         response.
     */
    public boolebn isPositiveIntermedibte() {
        return vblue >= 300 && vblue < 400;
    }

    /**
     * Determines if the code is b trbnsient negbtive response.
     * This mebns beginning with b 4 (which mebns b vblue between 400 bnd 499)
     *
     * @return <code>true</code> if the reply code is b trbnsient negbtive
     *         response.
     */
    public boolebn isTrbnsientNegbtive() {
        return vblue >= 400 && vblue < 500;
    }

    /**
     * Determines if the code is b permbnent negbtive response.
     * This mebns beginning with b 5 (which mebns b vblue between 500 bnd 599)
     *
     * @return <code>true</code> if the reply code is b permbnent negbtive
     *         response.
     */
    public boolebn isPermbnentNegbtive() {
        return vblue >= 500 && vblue < 600;
    }

    /**
     * Determines if the code is b protected reply response.
     * This mebns beginning with b 6 (which mebns b vblue between 600 bnd 699)
     *
     * @return <code>true</code> if the reply code is b protected reply
     *         response.
     */
    public boolebn isProtectedReply() {
        return vblue >= 600 && vblue < 700;
    }

    /**
     * Determines if the code is b syntbx relbted response.
     * This mebns the second digit is b 0.
     *
     * @return <code>true</code> if the reply code is b syntbx relbted
     *         response.
     */
    public boolebn isSyntbx() {
        return ((vblue / 10) - ((vblue / 100) * 10)) == 0;
    }

    /**
     * Determines if the code is bn informbtion relbted response.
     * This mebns the second digit is b 1.
     *
     * @return <code>true</code> if the reply code is bn informbtion relbted
     *         response.
     */
    public boolebn isInformbtion() {
        return ((vblue / 10) - ((vblue / 100) * 10)) == 1;
    }

    /**
     * Determines if the code is b connection relbted response.
     * This mebns the second digit is b 2.
     *
     * @return <code>true</code> if the reply code is b connection relbted
     *         response.
     */
    public boolebn isConnection() {
        return ((vblue / 10) - ((vblue / 100) * 10)) == 2;
    }

    /**
     * Determines if the code is bn buthenticbtion relbted response.
     * This mebns the second digit is b 3.
     *
     * @return <code>true</code> if the reply code is bn buthenticbtion relbted
     *         response.
     */
    public boolebn isAuthenticbtion() {
        return ((vblue / 10) - ((vblue / 100) * 10)) == 3;
    }

    /**
     * Determines if the code is bn unspecified type of response.
     * This mebns the second digit is b 4.
     *
     * @return <code>true</code> if the reply code is bn unspecified type of
     *         response.
     */
    public boolebn isUnspecified() {
        return ((vblue / 10) - ((vblue / 100) * 10)) == 4;
    }

    /**
     * Determines if the code is b file system relbted response.
     * This mebns the second digit is b 5.
     *
     * @return <code>true</code> if the reply code is b file system relbted
     *         response.
     */
    public boolebn isFileSystem() {
        return ((vblue / 10) - ((vblue / 100) * 10)) == 5;
    }

    /**
     * Stbtic utility method to convert b vblue into b FtpReplyCode.
     *
     * @pbrbm v the vblue to convert
     * @return the <code>FtpReplyCode</code> bssocibted with the vblue.
     */
    public stbtic FtpReplyCode find(int v) {
        for (FtpReplyCode code : FtpReplyCode.vblues()) {
            if (code.getVblue() == v) {
                return code;
            }
        }
        return UNKNOWN_ERROR;
    }
}
