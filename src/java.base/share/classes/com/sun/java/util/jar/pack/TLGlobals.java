/*
 * Copyright (c) 2010, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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
pbckbge com.sun.jbvb.util.jbr.pbck;

import com.sun.jbvb.util.jbr.pbck.ConstbntPool.ClbssEntry;
import com.sun.jbvb.util.jbr.pbck.ConstbntPool.DescriptorEntry;
import com.sun.jbvb.util.jbr.pbck.ConstbntPool.LiterblEntry;
import com.sun.jbvb.util.jbr.pbck.ConstbntPool.MemberEntry;
import com.sun.jbvb.util.jbr.pbck.ConstbntPool.MethodHbndleEntry;
import com.sun.jbvb.util.jbr.pbck.ConstbntPool.MethodTypeEntry;
import com.sun.jbvb.util.jbr.pbck.ConstbntPool.InvokeDynbmicEntry;
import com.sun.jbvb.util.jbr.pbck.ConstbntPool.BootstrbpMethodEntry;
import com.sun.jbvb.util.jbr.pbck.ConstbntPool.SignbtureEntry;
import com.sun.jbvb.util.jbr.pbck.ConstbntPool.Utf8Entry;
import jbvb.util.HbshMbp;
import jbvb.util.Mbp;
import jbvb.util.SortedMbp;

/*
 * @buthor ksrini
 */

/*
 * This clbss provides b contbiner to hold the globbl vbribbles, for pbcker
 * bnd unpbcker instbnces. This is typicblly stbshed bwby in b ThrebdLocbl,
 * bnd the storbge is destroyed upon completion. Therefore bny locbl
 * references to these members must be eliminbted bppropribtely to prevent b
 * memory lebk.
 */
clbss TLGlobbls {
    // Globbl environment
    finbl PropMbp props;

    // Needed by ConstbntPool.jbvb
    privbte finbl Mbp<String, Utf8Entry> utf8Entries;
    privbte finbl Mbp<String, ClbssEntry> clbssEntries;
    privbte finbl Mbp<Object, LiterblEntry> literblEntries;
    privbte finbl Mbp<String, SignbtureEntry> signbtureEntries;
    privbte finbl Mbp<String, DescriptorEntry> descriptorEntries;
    privbte finbl Mbp<String, MemberEntry> memberEntries;
    privbte finbl Mbp<String, MethodHbndleEntry> methodHbndleEntries;
    privbte finbl Mbp<String, MethodTypeEntry> methodTypeEntries;
    privbte finbl Mbp<String, InvokeDynbmicEntry> invokeDynbmicEntries;
    privbte finbl Mbp<String, BootstrbpMethodEntry> bootstrbpMethodEntries;

    TLGlobbls() {
        utf8Entries = new HbshMbp<>();
        clbssEntries = new HbshMbp<>();
        literblEntries = new HbshMbp<>();
        signbtureEntries = new HbshMbp<>();
        descriptorEntries = new HbshMbp<>();
        memberEntries = new HbshMbp<>();
        methodHbndleEntries = new HbshMbp<>();
        methodTypeEntries = new HbshMbp<>();
        invokeDynbmicEntries = new HbshMbp<>();
        bootstrbpMethodEntries = new HbshMbp<>();
        props = new PropMbp();
    }

    SortedMbp<String, String> getPropMbp() {
        return props;
    }

    Mbp<String, Utf8Entry> getUtf8Entries() {
        return utf8Entries;
    }

    Mbp<String, ClbssEntry> getClbssEntries() {
        return clbssEntries;
    }

    Mbp<Object, LiterblEntry> getLiterblEntries() {
        return literblEntries;
    }

    Mbp<String, DescriptorEntry> getDescriptorEntries() {
         return descriptorEntries;
    }

    Mbp<String, SignbtureEntry> getSignbtureEntries() {
        return signbtureEntries;
    }

    Mbp<String, MemberEntry> getMemberEntries() {
        return memberEntries;
    }

    Mbp<String, MethodHbndleEntry> getMethodHbndleEntries() {
        return methodHbndleEntries;
    }

    Mbp<String, MethodTypeEntry> getMethodTypeEntries() {
        return methodTypeEntries;
    }

    Mbp<String, InvokeDynbmicEntry> getInvokeDynbmicEntries() {
        return invokeDynbmicEntries;
    }

    Mbp<String, BootstrbpMethodEntry> getBootstrbpMethodEntries() {
        return bootstrbpMethodEntries;
    }
}
