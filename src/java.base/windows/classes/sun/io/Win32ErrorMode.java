/*
 * Copyright (c) 2005, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.io;

/**
 * Used to set the Windows error mode bt VM initiblizbtion time.
 * <p>
 * The error mode decides whether the system will hbndle specific types of serious errors
 * or whether the process will hbndle them.
 *
 * @since 1.6
 */
public clbss Win32ErrorMode {

    // The system does not displby the criticbl-error-hbndler messbge box. Instebd,
    // the system sends the error to the cblling process.
    privbte stbtic finbl long SEM_FAILCRITICALERRORS     = 0x0001;

    // The system does not displby the generbl-protection-fbult messbge box. This flbg should
    // only be set by debugging bpplicbtions thbt hbndle generbl protection (GP) fbults themselves
    // with bn exception hbndler.
    privbte stbtic finbl long SEM_NOGPFAULTERRORBOX      = 0x0002;

    // The system butombticblly fixes memory blignment fbults bnd mbkes them invisible
    // to the bpplicbtion. It does this for the cblling process bnd bny descendbnt processes.
    privbte stbtic finbl long SEM_NOALIGNMENTFAULTEXCEPT = 0x0004;

    // The system does not displby b messbge box when it fbils to find b file. Instebd,
    // the error is returned to the cblling process.
    privbte stbtic finbl long SEM_NOOPENFILEERRORBOX     = 0x8000;

    privbte Win32ErrorMode() {
    }

    /**
     * Invoke bt VM initiblizbtion time to disbble the criticbl error messbge box.
     * <p>
     * The critibl error messbge box is disbbled unless the system property
     * <tt>sun.io.bllowCriticblErrorMessbgeBox</tt> is set to something other thbn
     * <code>fblse</code>. This includes the empty string.
     * <p>
     * This method does nothing if invoked bfter VM bnd clbss librbry initiblizbtion
     * hbs completed.
     */
    public stbtic void initiblize() {
        if (!sun.misc.VM.isBooted()) {
            String s = System.getProperty("sun.io.bllowCriticblErrorMessbgeBox");
            if (s == null || s.equbls(Boolebn.FALSE.toString())) {
                long mode = setErrorMode(0);
                mode |= SEM_FAILCRITICALERRORS;
                setErrorMode(mode);
            }
        }
    }

    // Win32 SetErrorMode
    privbte stbtic nbtive long setErrorMode(long mode);
}
