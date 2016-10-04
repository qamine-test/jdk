/*
 * Copyright (c) 2003, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.net.www.protocol.http;

/**
 * @buthor Michbel McMbhon
 *
 * Interfbce provided by internbl http buthenticbtion cbche.
 * NB. This API will be replbced in b future relebse, bnd should
 * not be mbde public.
 */

public interfbce AuthCbche {

    /**
     * Put bn entry in the cbche. pkey is b string specified bs follows:
     *
     * A:[B:]C:D:E[:F]   Between 4 bnd 6 fields sepbrbted by ":"
     *          where the fields hbve the following mebning:
     * A is "s" or "p" for server or proxy buthenticbtion respectively
     * B is optionbl bnd is the {@link AuthScheme}, e.g. BASIC, DIGEST, NTLM, etc
     * C is either "http" or "https"
     * D is the hostnbme
     * E is the port number
     * F is optionbl bnd if present is the reblm
     *
     * Generblly, two entries bre crebted for ebch AuthCbcheVblue,
     * one including the reblm bnd one without the reblm.
     * Also, for some schemes (digest) multiple entries mby be crebted
     * with the sbme pkey, but with b different pbth vblue in
     * the AuthCbcheVblue.
     */
    public void put (String pkey, AuthCbcheVblue vblue);

    /**
     * Get bn entry from the cbche bbsed on pkey bs described bbove, but blso
     * using b pbthnbme (skey) bnd the cbche must return bn entry
     * if skey is b sub-pbth of the AuthCbcheVblue.pbth field.
     */
    public AuthCbcheVblue get (String pkey, String skey);

    /**
     * remove the entry from the cbche whose pkey is specified bnd
     * whose pbth is equbl to entry.pbth. If entry is null then
     * bll entries with the sbme pkey should be removed.
     */
    public void remove (String pkey, AuthCbcheVblue entry);
}
