import React, { useRef, useState } from 'react';

function Project() {
    const canvasRef = useRef(null);
    const [x0, setX0] = useState(0);
    const [y0, setY0] = useState(0);
    const [dragging, setDragging] = useState(false);

    const handleMouseDown = (e) => {
        setDragging(true);
        const canvas = canvasRef.current;
        if (canvas) {
            setX0(e.nativeEvent.offsetX);
            setY0(e.nativeEvent.offsetY);
        }
    };

    const handleMouseMove = (e) => {
        if (dragging) {
            const canvas = canvasRef.current;
            if (canvas) {
                const ctx = canvas.getContext("2d");

                // Clear the canvas before redrawing
                ctx.clearRect(0, 0, canvas.width, canvas.height);

                const x = e.nativeEvent.offsetX;
                const y = e.nativeEvent.offsetY;

                const width = x - x0;
                const height = y - y0;

                ctx.strokeStyle = `rgb(${0}, ${102}, ${25})`;
                ctx.beginPath();
                ctx.rect(x0, y0, width, height);
                ctx.stroke();
            }
        }
    };

    const handleMouseUp = () => {
        setDragging(false);
    };

    return (
        <div className="project_container">
            <nav className="project_tools_nav">
                {/* TODO: add tools like save, gen-button, ...*/}
                <ul className='list tool'>
                    <li></li>
                </ul>
                <button>To SQL</button>
                <div className='entity tool'></div>
                <div className='relationship tool'></div>
                <div className='attribute tool'></div>
                <div className='line tool'></div>
            </nav>
            <main className="project_main">
                <section className="er_tools_section">
                    {/* TODO: ER tools here, entity, relation , ... */}
                </section>
                <section className="canvas_container">
                    <div className="drawing_area"
                        onMouseMove={handleMouseMove}
                        onMouseUp={handleMouseUp}
                        onMouseDown={handleMouseDown}
                    >
                        <canvas
                            ref={canvasRef}
                            className="project_canvas"
                            width={800} // Set your desired canvas width
                            height={600} // Set your desired canvas height
                        >
                        </canvas>
                    </div>
                </section>
            </main>
        </div>
    );
}

export default Project;
