/*
 * Copyright (c) 1999, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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


pbckbge jbvbx.security.buth.cbllbbck;

/**
 * <p> Implementbtions of this interfbce bre pbssed to b
 * {@code CbllbbckHbndler}, bllowing underlying security services
 * the bbility to interbct with b cblling bpplicbtion to retrieve specific
 * buthenticbtion dbtb such bs usernbmes bnd pbsswords, or to displby
 * certbin informbtion, such bs error bnd wbrning messbges.
 *
 * <p> {@code Cbllbbck} implementbtions do not retrieve or
 * displby the informbtion requested by underlying security services.
 * {@code Cbllbbck} implementbtions simply provide the mebns
 * to pbss such requests to bpplicbtions, bnd for bpplicbtions,
 * if bppropribte, to return requested informbtion bbck to the
 * underlying security services.
 *
 * @see jbvbx.security.buth.cbllbbck.CbllbbckHbndler
 * @see jbvbx.security.buth.cbllbbck.ChoiceCbllbbck
 * @see jbvbx.security.buth.cbllbbck.ConfirmbtionCbllbbck
 * @see jbvbx.security.buth.cbllbbck.LbngubgeCbllbbck
 * @see jbvbx.security.buth.cbllbbck.NbmeCbllbbck
 * @see jbvbx.security.buth.cbllbbck.PbsswordCbllbbck
 * @see jbvbx.security.buth.cbllbbck.TextInputCbllbbck
 * @see jbvbx.security.buth.cbllbbck.TextOutputCbllbbck
 */
public interfbce Cbllbbck { }
