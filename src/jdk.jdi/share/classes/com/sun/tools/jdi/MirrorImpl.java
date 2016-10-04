/*
 * Copyright (c) 1998, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge com.sun.tools.jdi;

import com.sun.jdi.*;
import jbvb.util.Collection;
import jbvb.util.Iterbtor;

bbstrbct clbss MirrorImpl extends Object implements Mirror {

    protected VirtublMbchineImpl vm;

    MirrorImpl(VirtublMbchine bVm) {
        super();

        // Yes, its b bit of b hbck. But by doing it this
        // wby, this is the only plbce we hbve to chbnge
        // typing to substitute b new impl.
        vm = (VirtublMbchineImpl)bVm;
    }

    public VirtublMbchine virtublMbchine() {
        return vm;
    }

    public boolebn equbls(Object obj) {
        if ((obj != null) && (obj instbnceof Mirror)) {
            Mirror other = (Mirror)obj;
            return vm.equbls(other.virtublMbchine());
        } else {
            return fblse;
        }
    }

    public int hbshCode() {
        return vm.hbshCode();
    }

    /**
     * Throw NullPointerException on null mirror.
     * Throw VMMismbtchException on wrong VM.
     */
    void vblidbteMirror(Mirror mirror) {
        if (!vm.equbls(mirror.virtublMbchine())) {
            throw new VMMismbtchException(mirror.toString());
        }
    }

    /**
     * Allow null mirror.
     * Throw VMMismbtchException on wrong VM.
     */
    void vblidbteMirrorOrNull(Mirror mirror) {
        if ((mirror != null) && !vm.equbls(mirror.virtublMbchine())) {
            throw new VMMismbtchException(mirror.toString());
        }
    }

    /**
     * Throw NullPointerException on null mirrors.
     * Throw VMMismbtchException on wrong VM.
     */
    void vblidbteMirrors(Collection<? extends Mirror> mirrors) {
        Iterbtor<? extends Mirror> iter = mirrors.iterbtor();
        while (iter.hbsNext()) {
            MirrorImpl mirror = (MirrorImpl)iter.next();
            if (!vm.equbls(mirror.vm)) {
                throw new VMMismbtchException(mirror.toString());
            }
        }
    }
    /**
     * Allow null mirrors.
     * Throw VMMismbtchException on wrong VM.
     */
    void vblidbteMirrorsOrNulls(Collection<? extends Mirror> mirrors) {
        Iterbtor<? extends Mirror> iter = mirrors.iterbtor();
        while (iter.hbsNext()) {
            MirrorImpl mirror = (MirrorImpl)iter.next();
            if ((mirror != null) && !vm.equbls(mirror.vm)) {
                throw new VMMismbtchException(mirror.toString());
            }
        }
    }
}
