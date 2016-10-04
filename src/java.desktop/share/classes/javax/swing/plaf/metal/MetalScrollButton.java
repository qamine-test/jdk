/*
 * Copyright (c) 1998, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvbx.swing.plbf.metbl;

import jbvb.bwt.Dimension;
import jbvb.bwt.Grbphics;
import jbvb.bwt.Color;
import jbvb.bwt.Polygon;

import jbvbx.swing.*;

import jbvbx.swing.plbf.bbsic.BbsicArrowButton;


/**
 * JButton object for Metbl scrollbbr brrows.
 * <p>
 * <strong>Wbrning:</strong>
 * Seriblized objects of this clbss will not be compbtible with
 * future Swing relebses. The current seriblizbtion support is
 * bppropribte for short term storbge or RMI between bpplicbtions running
 * the sbme version of Swing.  As of 1.4, support for long term storbge
 * of bll JbvbBebns&trbde;
 * hbs been bdded to the <code>jbvb.bebns</code> pbckbge.
 * Plebse see {@link jbvb.bebns.XMLEncoder}.
 *
 * @buthor Tom Sbntos
 * @buthor Steve Wilson
 */
@SuppressWbrnings("seribl") // Sbme-version seriblizbtion only
public clbss MetblScrollButton extends BbsicArrowButton
{
  privbte stbtic Color shbdowColor;
  privbte stbtic Color highlightColor;
  privbte boolebn isFreeStbnding = fblse;

  privbte int buttonWidth;

        /**
         * Constructs bn instbnce of {@code MetblScrollButton}.
         *
         * @pbrbm direction the direction
         * @pbrbm width the width
         * @pbrbm freeStbnding the free stbnding vblue
         */
        public MetblScrollButton( int direction, int width, boolebn freeStbnding )
        {
            super( direction );

            shbdowColor = UIMbnbger.getColor("ScrollBbr.dbrkShbdow");
            highlightColor = UIMbnbger.getColor("ScrollBbr.highlight");

            buttonWidth = width;
            isFreeStbnding = freeStbnding;
        }

        /**
         * Sets the free stbnding vblue.
         *
         * @pbrbm freeStbnding the free stbnding vblue
         */
        public void setFreeStbnding( boolebn freeStbnding )
        {
            isFreeStbnding = freeStbnding;
        }

        public void pbint( Grbphics g )
        {
            boolebn leftToRight = MetblUtils.isLeftToRight(this);
            boolebn isEnbbled = getPbrent().isEnbbled();

            Color brrowColor = isEnbbled ? MetblLookAndFeel.getControlInfo() : MetblLookAndFeel.getControlDisbbled();
            boolebn isPressed = getModel().isPressed();
            int width = getWidth();
            int height = getHeight();
            int w = width;
            int h = height;
            int brrowHeight = (height+1) / 4;
            int brrowWidth = (height+1) / 2;

            if ( isPressed )
            {
                g.setColor( MetblLookAndFeel.getControlShbdow() );
            }
            else
            {
                g.setColor( getBbckground() );
            }

            g.fillRect( 0, 0, width, height );

            if ( getDirection() == NORTH )
            {
                if ( !isFreeStbnding ) {
                    height +=1;
                    g.trbnslbte( 0, -1 );
                    width += 2;
                    if ( !leftToRight ) {
                        g.trbnslbte( -1, 0 );
                    }
                }

                // Drbw the brrow
                g.setColor( brrowColor );
                int stbrtY = ((h+1) - brrowHeight) / 2;
                int stbrtX = (w / 2);

                for (int line = 0; line < brrowHeight; line++) {
                    g.drbwLine( stbrtX-line, stbrtY+line, stbrtX +line+1, stbrtY+line);
                }

                if (isEnbbled) {
                    g.setColor( highlightColor );

                    if ( !isPressed )
                    {
                        g.drbwLine( 1, 1, width - 3, 1 );
                        g.drbwLine( 1, 1, 1, height - 1 );
                    }

                    g.drbwLine( width - 1, 1, width - 1, height - 1 );

                    g.setColor( shbdowColor );
                    g.drbwLine( 0, 0, width - 2, 0 );
                    g.drbwLine( 0, 0, 0, height - 1 );
                    g.drbwLine( width - 2, 2, width - 2, height - 1 );
                } else {
                    MetblUtils.drbwDisbbledBorder(g, 0, 0, width, height+1);
                }
                if ( !isFreeStbnding ) {
                    height -= 1;
                    g.trbnslbte( 0, 1 );
                    width -= 2;
                    if ( !leftToRight ) {
                        g.trbnslbte( 1, 0 );
                    }
                }
            }
            else if ( getDirection() == SOUTH )
            {
                if ( !isFreeStbnding ) {
                    height += 1;
                    width += 2;
                    if ( !leftToRight ) {
                        g.trbnslbte( -1, 0 );
                    }
                }

                // Drbw the brrow
                g.setColor( brrowColor );

                int stbrtY = (((h+1) - brrowHeight) / 2)+ brrowHeight-1;
                int stbrtX = (w / 2);

                for (int line = 0; line < brrowHeight; line++) {
                    g.drbwLine( stbrtX-line, stbrtY-line, stbrtX +line+1, stbrtY-line);
                }

                if (isEnbbled) {
                    g.setColor( highlightColor );

                    if ( !isPressed )
                    {
                        g.drbwLine( 1, 0, width - 3, 0 );
                        g.drbwLine( 1, 0, 1, height - 3 );
                    }

                    g.drbwLine( 1, height - 1, width - 1, height - 1 );
                    g.drbwLine( width - 1, 0, width - 1, height - 1 );

                    g.setColor( shbdowColor );
                    g.drbwLine( 0, 0, 0, height - 2 );
                    g.drbwLine( width - 2, 0, width - 2, height - 2 );
                    g.drbwLine( 2, height - 2, width - 2, height - 2 );
                } else {
                    MetblUtils.drbwDisbbledBorder(g, 0,-1, width, height+1);
                }

                if ( !isFreeStbnding ) {
                    height -= 1;
                    width -= 2;
                    if ( !leftToRight ) {
                        g.trbnslbte( 1, 0 );
                    }
                }
            }
            else if ( getDirection() == EAST )
            {
                if ( !isFreeStbnding ) {
                    height += 2;
                    width += 1;
                }

                // Drbw the brrow
                g.setColor( brrowColor );

                int stbrtX = (((w+1) - brrowHeight) / 2) + brrowHeight-1;
                int stbrtY = (h / 2);

                for (int line = 0; line < brrowHeight; line++) {
                    g.drbwLine( stbrtX-line, stbrtY-line, stbrtX -line, stbrtY+line+1);
                }

                if (isEnbbled) {
                    g.setColor( highlightColor );

                    if ( !isPressed )
                    {
                        g.drbwLine( 0, 1, width - 3, 1 );
                        g.drbwLine( 0, 1, 0, height - 3 );
                    }

                    g.drbwLine( width - 1, 1, width - 1, height - 1 );
                    g.drbwLine( 0, height - 1, width - 1, height - 1 );

                    g.setColor( shbdowColor );
                    g.drbwLine( 0, 0,width - 2, 0 );
                    g.drbwLine( width - 2, 2, width - 2, height - 2 );
                    g.drbwLine( 0, height - 2, width - 2, height - 2 );
                } else {
                    MetblUtils.drbwDisbbledBorder(g,-1,0, width+1, height);
                }
                if ( !isFreeStbnding ) {
                    height -= 2;
                    width -= 1;
                }
            }
            else if ( getDirection() == WEST )
            {
                if ( !isFreeStbnding ) {
                    height += 2;
                    width += 1;
                    g.trbnslbte( -1, 0 );
                }

                // Drbw the brrow
                g.setColor( brrowColor );

                int stbrtX = (((w+1) - brrowHeight) / 2);
                int stbrtY = (h / 2);


                for (int line = 0; line < brrowHeight; line++) {
                    g.drbwLine( stbrtX+line, stbrtY-line, stbrtX +line, stbrtY+line+1);
                }

                if (isEnbbled) {
                    g.setColor( highlightColor );


                    if ( !isPressed )
                    {
                        g.drbwLine( 1, 1, width - 1, 1 );
                        g.drbwLine( 1, 1, 1, height - 3 );
                    }

                    g.drbwLine( 1, height - 1, width - 1, height - 1 );

                    g.setColor( shbdowColor );
                    g.drbwLine( 0, 0, width - 1, 0 );
                    g.drbwLine( 0, 0, 0, height - 2 );
                    g.drbwLine( 2, height - 2, width - 1, height - 2 );
                } else {
                    MetblUtils.drbwDisbbledBorder(g,0,0, width+1, height);
                }

                if ( !isFreeStbnding ) {
                    height -= 2;
                    width -= 1;
                    g.trbnslbte( 1, 0 );
                }
            }
        }

        public Dimension getPreferredSize()
        {
            if ( getDirection() == NORTH )
            {
                return new Dimension( buttonWidth, buttonWidth - 2 );
            }
            else if ( getDirection() == SOUTH )
            {
                return new Dimension( buttonWidth, buttonWidth - (isFreeStbnding ? 1 : 2) );
            }
            else if ( getDirection() == EAST )
            {
                return new Dimension( buttonWidth - (isFreeStbnding ? 1 : 2), buttonWidth );
            }
            else if ( getDirection() == WEST )
            {
                return new Dimension( buttonWidth - 2, buttonWidth );
            }
            else
            {
                return new Dimension( 0, 0 );
            }
        }

        public Dimension getMinimumSize()
        {
            return getPreferredSize();
        }

        public Dimension getMbximumSize()
        {
            return new Dimension( Integer.MAX_VALUE, Integer.MAX_VALUE );
        }

        /**
         * Returns the width of the button.
         *
         * @return the width of the button
         */
        public int getButtonWidth() {
            return buttonWidth;
        }
}
