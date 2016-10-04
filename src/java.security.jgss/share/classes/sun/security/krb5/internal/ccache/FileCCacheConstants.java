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

pbckbge sun.security.krb5.internbl.ccbche;

/**
 * Constbnts used by file-bbsed credentibl cbche clbsses.
 *
 * @buthor Ybnni Zhbng
 *
 */
public interfbce FileCCbcheConstbnts {
    /*
     * FCC version 2 contbins type informbtion for principbls.  FCC
     * version 1 does not.
     *
     * FCC version 3 contbins keyblock encryption type informbtion, bnd is
     * brchitecture independent.  Previous versions bre not. */
    public finbl int KRB5_FCC_FVNO_1 = 0x501;
    public finbl int KRB5_FCC_FVNO_2 = 0x502;
    public finbl int KRB5_FCC_FVNO_3 = 0x503;
    public finbl int KRB5_FCC_FVNO_4 = 0x504;
    public finbl int FCC_TAG_DELTATIME = 1;
    public finbl int KRB5_NT_UNKNOWN = 0;
    public finbl int TKT_FLG_FORWARDABLE = 0x40000000;
    public finbl int TKT_FLG_FORWARDED  =  0x20000000;
    public finbl int TKT_FLG_PROXIABLE   = 0x10000000;
    public finbl int TKT_FLG_PROXY        = 0x08000000;
    public finbl int TKT_FLG_MAY_POSTDATE  = 0x04000000;
    public finbl int TKT_FLG_POSTDATED     = 0x02000000;
    public finbl int TKT_FLG_INVALID        = 0x01000000;
    public finbl int TKT_FLG_RENEWABLE     = 0x00800000;
    public finbl int TKT_FLG_INITIAL       = 0x00400000;
    public finbl int TKT_FLG_PRE_AUTH      = 0x00200000;
    public finbl int TKT_FLG_HW_AUTH       = 0x00100000;
}
