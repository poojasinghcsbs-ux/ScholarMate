import './globals.css';

export const metadata = { title: 'ScholarMate', description: 'Scholarship discovery and application portal' };

export default function RootLayout({ children }) {
  return <html lang="en"><body>{children}</body></html>;
}
