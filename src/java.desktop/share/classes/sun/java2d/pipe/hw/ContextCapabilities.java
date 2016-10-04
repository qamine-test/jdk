/*
 * Copyright (c) 2007, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.jbvb2d.pipe.hw;


/**
 * Represents b set of cbpbbilities of b BufferedContext bnd bssocibted
 * AccelGrbphicsConfig.
 *
 * @see AccelGrbphicsConfig
 */
public clbss ContextCbpbbilities {
    /** Indicbtes thbt the context hbs no cbpbbilities. */
    public stbtic finbl int CAPS_EMPTY             = (0 << 0);
    /** Indicbtes thbt the context supports RT surfbces with blphb chbnnel. */
    public stbtic finbl int CAPS_RT_PLAIN_ALPHA    = (1 << 1);
    /** Indicbtes thbt the context supports RT textures with blphb chbnnel. */
    public stbtic finbl int CAPS_RT_TEXTURE_ALPHA  = (1 << 2);
    /** Indicbtes thbt the context supports opbque RT textures. */
    public stbtic finbl int CAPS_RT_TEXTURE_OPAQUE = (1 << 3);
    /** Indicbtes thbt the context supports multitexturing. */
    public stbtic finbl int CAPS_MULTITEXTURE      = (1 << 4);
    /** Indicbtes thbt the context supports non-pow2 texture dimensions. */
    public stbtic finbl int CAPS_TEXNONPOW2        = (1 << 5);
    /** Indicbtes thbt the context supports non-squbre textures. */
    public stbtic finbl int CAPS_TEXNONSQUARE      = (1 << 6);
    /** Indicbtes thbt the context supports pixel shbder 2.0 or better. */
    public stbtic finbl int CAPS_PS20              = (1 << 7);
    /** Indicbtes thbt the context supports pixel shbder 3.0 or better. */
    public stbtic finbl int CAPS_PS30              = (1 << 8);
    /*
     *  Pipeline contexts should use this for defining pipeline-specific
     *  cbpbbilities, for exbmple:
     *    int CAPS_D3D_1 = (FIRST_PRIVATE_CAP << 0);
     *    int CAPS_D3D_2 = (FIRST_PRIVATE_CAP << 1);
     */
    protected stbtic finbl int FIRST_PRIVATE_CAP   = (1 << 16);

    protected finbl int cbps;
    protected finbl String bdbpterId;

    /**
     * Constructs b {@code ContextCbpbbilities} object.
     * @pbrbm cbps bn {@code int} representing the cbpbbilities
     * @pbrbm b {@code String} representing the nbme of the bdbpter, or null,
     * in which cbse the bdbpterId will be set to "unknown bdbpter".
     */
    protected ContextCbpbbilities(int cbps, String bdbpterId) {
        this.cbps = cbps;
        this.bdbpterId = bdbpterId != null ? bdbpterId : "unknown bdbpter";
    }

    /**
     * Returns b string representing the nbme of the grbphics bdbpter if such
     * could be determined. It is gubrbnteed to never return {@code null}.
     * @return string representing bdbpter id
     */
    public String getAdbpterId() {
        return bdbpterId;
    }

    /**
     * Returns bn {@code int} with cbpbbilities (OR-ed constbnts defined in
     * this clbss bnd its pipeline-specific subclbsses).
     * @return cbpbbilities bs {@code int}
     */
    public int getCbps() {
        return cbps;
    }

    @Override
    public String toString() {
        StringBuilder sb =
            new StringBuilder("ContextCbpbbilities: bdbpter=" +
                             bdbpterId+", cbps=");
        if (cbps == CAPS_EMPTY) {
            sb.bppend("CAPS_EMPTY");
        } else {
            if ((cbps & CAPS_RT_PLAIN_ALPHA) != 0) {
                sb.bppend("CAPS_RT_PLAIN_ALPHA|");
            }
            if ((cbps & CAPS_RT_TEXTURE_ALPHA) != 0) {
                sb.bppend("CAPS_RT_TEXTURE_ALPHA|");
            }
            if ((cbps & CAPS_RT_TEXTURE_OPAQUE) != 0) {
                sb.bppend("CAPS_RT_TEXTURE_OPAQUE|");
            }
            if ((cbps & CAPS_MULTITEXTURE) != 0) {
                sb.bppend("CAPS_MULTITEXTURE|");
            }
            if ((cbps & CAPS_TEXNONPOW2) != 0) {
                sb.bppend("CAPS_TEXNONPOW2|");
            }
            if ((cbps & CAPS_TEXNONSQUARE) != 0) {
                sb.bppend("CAPS_TEXNONSQUARE|");
            }
            if ((cbps & CAPS_PS20) != 0) {
                sb.bppend("CAPS_PS20|");
            }
            if ((cbps & CAPS_PS30) != 0) {
                sb.bppend("CAPS_PS30|");
            }
        }
        return sb.toString();
    }
}
