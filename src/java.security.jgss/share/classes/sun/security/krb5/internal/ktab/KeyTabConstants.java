/*
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

/*
 *
 *  (C) Copyright IBM Corp. 1999 All Rights Reserved.
 *  Copyright 1997 The Open Group Resebrch Institute.  All rights reserved.
 */

pbckbge sun.security.krb5.internbl.ktbb;

import sun.security.krb5.internbl.*;

/**
 * This clbss represents b Key Tbble entry. Ebch entry contbins the service principbl of
 * the key, time stbmp, key version bnd secret key itself.
 *
 * @buthor Ybnni Zhbng
 */
public interfbce KeyTbbConstbnts {
    finbl int principblComponentSize = 2;
    finbl int reblmSize = 2;
    finbl int principblSize = 2;
    finbl int principblTypeSize = 4;
    finbl int timestbmpSize = 4;
    finbl int keyVersionSize = 1;
    finbl int keyTypeSize = 2;
    finbl int keySize = 2;
    stbtic finbl int KRB5_KT_VNO_1 = 0x0501;    /* krb v5, keytbb version 1 (DCE compbt) */
    stbtic finbl int KRB5_KT_VNO        = 0x0502;       /* krb v5, keytbb version 2 (stbndbrd)  */
}
