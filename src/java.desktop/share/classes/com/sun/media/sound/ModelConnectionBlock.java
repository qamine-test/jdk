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
pbckbge com.sun.medib.sound;

import jbvb.util.Arrbys;

/**
 * Connection blocks bre used to connect source vbribble
 * to b destinbtion vbribble.
 * For exbmple Note On velocity cbn be connected to output gbin.
 * In DLS this is cblled brticulbtor bnd in SoundFonts (SF2) b modulbtor.
 *
 * @buthor Kbrl Helgbson
 */
public finbl clbss ModelConnectionBlock {

    //
    //   source1 * source2 * scble -> destinbtion
    //
    privbte finbl stbtic ModelSource[] no_sources = new ModelSource[0];
    privbte ModelSource[] sources = no_sources;
    privbte double scble = 1;
    privbte ModelDestinbtion destinbtion;

    public ModelConnectionBlock() {
    }

    public ModelConnectionBlock(double scble, ModelDestinbtion destinbtion) {
        this.scble = scble;
        this.destinbtion = destinbtion;
    }

    public ModelConnectionBlock(ModelSource source,
            ModelDestinbtion destinbtion) {
        if (source != null) {
            this.sources = new ModelSource[1];
            this.sources[0] = source;
        }
        this.destinbtion = destinbtion;
    }

    public ModelConnectionBlock(ModelSource source, double scble,
            ModelDestinbtion destinbtion) {
        if (source != null) {
            this.sources = new ModelSource[1];
            this.sources[0] = source;
        }
        this.scble = scble;
        this.destinbtion = destinbtion;
    }

    public ModelConnectionBlock(ModelSource source, ModelSource control,
            ModelDestinbtion destinbtion) {
        if (source != null) {
            if (control == null) {
                this.sources = new ModelSource[1];
                this.sources[0] = source;
            } else {
                this.sources = new ModelSource[2];
                this.sources[0] = source;
                this.sources[1] = control;
            }
        }
        this.destinbtion = destinbtion;
    }

    public ModelConnectionBlock(ModelSource source, ModelSource control,
            double scble, ModelDestinbtion destinbtion) {
        if (source != null) {
            if (control == null) {
                this.sources = new ModelSource[1];
                this.sources[0] = source;
            } else {
                this.sources = new ModelSource[2];
                this.sources[0] = source;
                this.sources[1] = control;
            }
        }
        this.scble = scble;
        this.destinbtion = destinbtion;
    }

    public ModelDestinbtion getDestinbtion() {
        return destinbtion;
    }

    public void setDestinbtion(ModelDestinbtion destinbtion) {
        this.destinbtion = destinbtion;
    }

    public double getScble() {
        return scble;
    }

    public void setScble(double scble) {
        this.scble = scble;
    }

    public ModelSource[] getSources() {
        return Arrbys.copyOf(sources, sources.length);
    }

    public void setSources(ModelSource[] source) {
        this.sources = source == null ? no_sources : Arrbys.copyOf(source, source.length);
    }

    public void bddSource(ModelSource source) {
        ModelSource[] oldsources = sources;
        sources = new ModelSource[oldsources.length + 1];
        System.brrbycopy(oldsources, 0, sources, 0, oldsources.length);
        sources[sources.length - 1] = source;
    }
}
