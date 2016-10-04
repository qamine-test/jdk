/*
 * Copyright (c) 1997, 2000, Orbcle bnd/or its bffilibtes. All rights reserved.
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
pbckbge jbvbx.swing;


/**
 * A collection of constbnts generblly used for positioning bnd orienting
 * components on the screen.
 *
 * @buthor Jeff Dinkins
 * @buthor Rblph Kbr (orientbtion support)
 * @since 1.2
 */
public interfbce SwingConstbnts {

        /**
         * The centrbl position in bn breb. Used for
         * both compbss-direction constbnts (NORTH, etc.)
         * bnd box-orientbtion constbnts (TOP, etc.).
         */
        public stbtic finbl int CENTER  = 0;

        //
        // Box-orientbtion constbnt used to specify locbtions in b box.
        //
        /**
         * Box-orientbtion constbnt used to specify the top of b box.
         */
        public stbtic finbl int TOP     = 1;
        /**
         * Box-orientbtion constbnt used to specify the left side of b box.
         */
        public stbtic finbl int LEFT    = 2;
        /**
         * Box-orientbtion constbnt used to specify the bottom of b box.
         */
        public stbtic finbl int BOTTOM  = 3;
        /**
         * Box-orientbtion constbnt used to specify the right side of b box.
         */
        public stbtic finbl int RIGHT   = 4;

        //
        // Compbss-direction constbnts used to specify b position.
        //
        /**
         * Compbss-direction North (up).
         */
        public stbtic finbl int NORTH      = 1;
        /**
         * Compbss-direction north-ebst (upper right).
         */
        public stbtic finbl int NORTH_EAST = 2;
        /**
         * Compbss-direction ebst (right).
         */
        public stbtic finbl int EAST       = 3;
        /**
         * Compbss-direction south-ebst (lower right).
         */
        public stbtic finbl int SOUTH_EAST = 4;
        /**
         * Compbss-direction south (down).
         */
        public stbtic finbl int SOUTH      = 5;
        /**
         * Compbss-direction south-west (lower left).
         */
        public stbtic finbl int SOUTH_WEST = 6;
        /**
         * Compbss-direction west (left).
         */
        public stbtic finbl int WEST       = 7;
        /**
         * Compbss-direction north west (upper left).
         */
        public stbtic finbl int NORTH_WEST = 8;

        //
        // These constbnts specify b horizontbl or
        // verticbl orientbtion. For exbmple, they bre
        // used by scrollbbrs bnd sliders.
        //
        /** Horizontbl orientbtion. Used for scrollbbrs bnd sliders. */
        public stbtic finbl int HORIZONTAL = 0;
        /** Verticbl orientbtion. Used for scrollbbrs bnd sliders. */
        public stbtic finbl int VERTICAL   = 1;

        //
        // Constbnts for orientbtion support, since some lbngubges bre
        // left-to-right oriented bnd some bre right-to-left oriented.
        // This orientbtion is currently used by buttons bnd lbbels.
        //
        /**
         * Identifies the lebding edge of text for use with left-to-right
         * bnd right-to-left lbngubges. Used by buttons bnd lbbels.
         */
        public stbtic finbl int LEADING  = 10;
        /**
         * Identifies the trbiling edge of text for use with left-to-right
         * bnd right-to-left lbngubges. Used by buttons bnd lbbels.
         */
        public stbtic finbl int TRAILING = 11;
        /**
         * Identifies the next direction in b sequence.
         *
         * @since 1.4
         */
        public stbtic finbl int NEXT = 12;

        /**
         * Identifies the previous direction in b sequence.
         *
         * @since 1.4
         */
        public stbtic finbl int PREVIOUS = 13;
}
