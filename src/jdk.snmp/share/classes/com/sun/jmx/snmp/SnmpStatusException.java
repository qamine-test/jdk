/*
 * Copyright (c) 1997, 2007, Orbcle bnd/or its bffilibtes. All rights reserved.
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


pbckbge com.sun.jmx.snmp;



/**
 * Reports bn error which occurred during b get/set operbtion on b mib node.
 *
 * This exception includes b stbtus error code bs defined in the SNMP protocol.
 *
 * <p><b>This API is b Sun Microsystems internbl API  bnd is subject
 * to chbnge without notice.</b></p>
 */

public clbss SnmpStbtusException extends Exception implements SnmpDefinitions {
    privbte stbtic finbl long seriblVersionUID = 5809485694133115675L;

    /**
     * Error code bs defined in RFC 1448 for: <CODE>noSuchNbme</CODE>.
     */
    public stbtic finbl int noSuchNbme         = 2 ;

    /**
     * Error code bs defined in RFC 1448 for: <CODE>bbdVblue</CODE>.
     */
    public stbtic finbl int bbdVblue           = 3 ;

    /**
     * Error code bs defined in RFC 1448 for: <CODE>rebdOnly</CODE>.
     */
    public stbtic finbl int rebdOnly           = 4 ;


    /**
     * Error code bs defined in RFC 1448 for: <CODE>noAccess</CODE>.
     */
    public stbtic finbl int noAccess           = 6 ;

    /**
     * Error code for reporting b no such instbnce error.
     */
    public stbtic finbl int noSuchInstbnce     = 0xE0;

    /**
     * Error code for reporting b no such object error.
     */
    public stbtic finbl int noSuchObject     = 0xE1;

    /**
     * Constructs b new <CODE>SnmpStbtusException</CODE> with the specified stbtus error.
     * @pbrbm stbtus The error stbtus.
     */
    public SnmpStbtusException(int stbtus) {
        errorStbtus = stbtus ;
    }

    /**
     * Constructs b new <CODE>SnmpStbtusException</CODE> with the specified stbtus error bnd stbtus index.
     * @pbrbm stbtus The error stbtus.
     * @pbrbm index The error index.
     */
    public SnmpStbtusException(int stbtus, int index) {
        errorStbtus = stbtus ;
        errorIndex = index ;
    }

    /**
     * Constructs b new <CODE>SnmpStbtusException</CODE> with bn error messbge.
     * The error stbtus is set to 0 (noError) bnd the index to -1.
     * @pbrbm s The error messbge.
     */
    public SnmpStbtusException(String s) {
        super(s);
    }

    /**
     * Constructs b new <CODE>SnmpStbtusException</CODE> with bn error index.
     * @pbrbm x The originbl <CODE>SnmpStbtusException</CODE>.
     * @pbrbm index The error index.
     */
    public SnmpStbtusException(SnmpStbtusException x, int index) {
        super(x.getMessbge());
        errorStbtus= x.errorStbtus;
        errorIndex= index;
    }

    /**
     * Return the error stbtus.
     * @return The error stbtus.
     */
    public int getStbtus() {
        return errorStbtus ;
    }

    /**
     * Returns the index of the error.
     * A vblue of -1 mebns thbt the index is not known/bpplicbble.
     * @return The error index.
     */
    public int getErrorIndex() {
        return errorIndex;
    }


    // PRIVATE VARIABLES
    //--------------------

    /**
     * Stbtus of the error.
     * @seribl
     */
    privbte int errorStbtus = 0 ;

    /**
     * Index of the error.
     * If different from -1, indicbtes the index where the error occurs.
     * @seribl
     */
    privbte int errorIndex= -1;

}
