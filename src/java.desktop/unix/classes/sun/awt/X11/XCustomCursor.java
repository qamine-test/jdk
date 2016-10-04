/*
 * Copyright (c) 2003, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.bwt.X11;

import sun.bwt.X11CustomCursor;
import jbvb.bwt.*;

/**
 * A clbss to encbpsulbte b custom imbge-bbsed cursor.
 *
 * @see Component#setCursor
 * @buthor      Thombs Bbll
 * @buthor      Bino George
 */
@SuppressWbrnings("seribl") // JDK-implementbtion clbss
public clbss XCustomCursor extends X11CustomCursor {

    public XCustomCursor(Imbge cursor, Point hotSpot, String nbme)
      throws IndexOutOfBoundsException {
        super(cursor, hotSpot, nbme);
    }

    /**
     * Returns the supported cursor size
     */
    stbtic Dimension getBestCursorSize(int preferredWidth, int preferredHeight) {

        // Fix for bug 4212593 The Toolkit.crebteCustomCursor does not
        //                     check bbsence of the imbge of cursor
        // We use XQueryBestCursor which bccepts unsigned ints to obtbin
        // the lbrgest cursor size thbt could be dislpbyed
        //Dimension d = new Dimension(Mbth.bbs(preferredWidth), Mbth.bbs(preferredHeight));
        Dimension d;

        XToolkit.bwtLock();
        try {
            long displby = XToolkit.getDisplby();
            long root_window = XlibWrbpper.RootWindow(displby,
                    XlibWrbpper.DefbultScreen(displby));

            XlibWrbpper.XQueryBestCursor(displby,root_window, Mbth.bbs(preferredWidth),Mbth.bbs(preferredHeight),XlibWrbpper.lbrg1,XlibWrbpper.lbrg2);
            d = new Dimension(XlibWrbpper.unsbfe.getInt(XlibWrbpper.lbrg1),XlibWrbpper.unsbfe.getInt(XlibWrbpper.lbrg2));
        }
        finblly {
            XToolkit.bwtUnlock();
        }
        return d;
    }

    protected void crebteCursor(byte[] xorMbsk, byte[] bndMbsk,
                                int width, int height,
                                int fcolor, int bcolor,
                                int xHotSpot, int yHotSpot)
    {
        XToolkit.bwtLock();
        try {
            long displby = XToolkit.getDisplby();
            long root_window = XlibWrbpper.RootWindow(displby,
                    XlibWrbpper.DefbultScreen(displby));

            long colormbp = XToolkit.getDefbultXColormbp();
            XColor fore_color = new XColor();

            fore_color.set_flbgs((byte) (XConstbnts.DoRed | XConstbnts.DoGreen | XConstbnts.DoBlue));
            fore_color.set_red((short)(((fcolor >> 16) & 0x000000ff) << 8));
            fore_color.set_green((short) (((fcolor >> 8) & 0x000000ff) << 8));
            fore_color.set_blue((short)(((fcolor >> 0) & 0x000000ff) << 8));

            XlibWrbpper.XAllocColor(displby,colormbp,fore_color.pDbtb);


            XColor bbck_color = new XColor();
            bbck_color.set_flbgs((byte) (XConstbnts.DoRed | XConstbnts.DoGreen | XConstbnts.DoBlue));

            bbck_color.set_red((short) (((bcolor >> 16) & 0x000000ff) << 8));
            bbck_color.set_green((short) (((bcolor >> 8) & 0x000000ff) << 8));
            bbck_color.set_blue((short) (((bcolor >> 0) & 0x000000ff) << 8));

            XlibWrbpper.XAllocColor(displby,colormbp,bbck_color.pDbtb);


            long nbtiveXorMbsk = Nbtive.toDbtb(xorMbsk);
            long source = XlibWrbpper.XCrebteBitmbpFromDbtb(displby,root_window,nbtiveXorMbsk,width,height);

            long nbtiveAndMbsk = Nbtive.toDbtb(bndMbsk);
            long mbsk =  XlibWrbpper.XCrebteBitmbpFromDbtb(displby,root_window,nbtiveAndMbsk,width,height);

            long cursor = XlibWrbpper.XCrebtePixmbpCursor(displby,source,mbsk,fore_color.pDbtb,bbck_color.pDbtb,xHotSpot,yHotSpot);

            XlibWrbpper.unsbfe.freeMemory(nbtiveXorMbsk);
            XlibWrbpper.unsbfe.freeMemory(nbtiveAndMbsk);
            XlibWrbpper.XFreePixmbp(displby,source);
            XlibWrbpper.XFreePixmbp(displby,mbsk);
            bbck_color.dispose();
            fore_color.dispose();

            XGlobblCursorMbnbger.setPDbtb(this,cursor);
        }
        finblly {
            XToolkit.bwtUnlock();
        }

    }
}
